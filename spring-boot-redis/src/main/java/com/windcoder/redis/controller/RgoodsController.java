package com.windcoder.redis.controller;

import com.windcoder.redis.entity.Rgoods;
import com.windcoder.redis.service.RgoodsService;
import com.windcoder.redis.utils.RedisLockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@Slf4j
public class RgoodsController {
	private static final int TIMEOUT = 5*1000;
	@Autowired
	private RgoodsService goodsService;
	@Autowired
	private RedisLockService redisLockService;
	AtomicInteger creNUm = new AtomicInteger(0);
	@PostMapping("")
	public Rgoods save(Rgoods rgoods){
		return goodsService.save(rgoods);
	}

	@GetMapping("{id}")
	public Rgoods getOne( @PathVariable("id") Long id){
		return goodsService.findOne(id).get();
	}

	@GetMapping("{id}/r")
	public Object getOneR( @PathVariable("id") Long id){
		return  redisLockService.getOne(String.valueOf(id));
	}

	@GetMapping("{id}/sell")
	public String Seckilling( @PathVariable("id") Long id, Long userId){
		log.error("抢购开始：{}", userId);
		//加锁
		long time = System.currentTimeMillis() + TIMEOUT;
		if(!redisLockService.tryLockByNative(String.valueOf(id),String.valueOf(time), 5, TimeUnit.SECONDS)){
			log.error("排队人数太多，请:{} 稍后再试",userId);
			return "排队人数太多，请:" + userId + "稍后再试.";
		}


		int surplusCount = 0;
		// 查询该商品库存，为0则活动结束 e.g. getStockByTargetId
		Rgoods goods = goodsService.findOne(id).get();
		surplusCount = goods.getNum();
		if(surplusCount==0){
			log.error("活动结束");
			return "活动结束.";
		}else {
			// 下单 e.g. buyStockByTargetId
			//减库存 不做处理的话，高并发下会出现超卖的情况，下单数，大于减库存的情况。虽然这里减了，但由于并发，减的库存还没存到map中去。新的并发拿到的是原来的库存
			surplusCount =surplusCount-1;
			goods.setNum(surplusCount);
			try{
				Thread.sleep(100);//模拟减库存的处理时间
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			// 减库存操作数据库 e.g. updateStockByTargetId
			goodsService.save(goods);
			creNUm.getAndIncrement();
			// buyStockByTargetId 和 updateStockByTargetId 可以同步完成(或者事物)，保证原子性。
		}

		//解锁
		redisLockService.unLockByNative(String.valueOf(id),String.valueOf(time));
		log.info("恭喜您:{}，秒杀成功:{}",userId, creNUm.get());
		return "恭喜您，秒杀成功。";

	}
}
