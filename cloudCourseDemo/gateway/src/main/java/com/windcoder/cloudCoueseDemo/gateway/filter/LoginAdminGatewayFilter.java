package com.windcoder.cloudCoueseDemo.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;

@Component
@Slf4j
public class LoginAdminGatewayFilter implements GatewayFilter, Ordered {

    @Resource
    private RedisTemplate redisTemplate;
    /**
     * 真正的实现
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // 请求地址中不包含/admin/的，不是控台请求，不需要拦截
        if (!path.contains("/admin/")) {
            return chain.filter(exchange);
        }
        if (path.contains("/system/admin/user/login")
                || path.contains("/system/admin/user/logout")
                || path.contains("/system/admin/kaptcha")) {
            log.info("不需要控制台登录验证：{}", path);
            return chain.filter(exchange);
        }
        // 获取header的token参数
        String token = exchange.getRequest().getHeaders().getFirst("token");
        log.info("控制台登录验证开始，token：{}", token);
        if (null == token  || token.isEmpty()) {
            log.info("token为空，请求被拦截");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        Object object = redisTemplate.opsForValue().get(token);
        if (null == token ) {
            log.warn("token无效，请求被拦截");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        } else {
            log.info("已登录：{}", object);
            // 增加权限校验，gateway里没有LoginUserDto，所以全部用JSON操作
            log.info("接口权限校验，请求地址：{}", path);
            boolean exist = false;
            JSONObject loginUser = JSON.parseObject(String.valueOf(object));
            JSONArray requests = loginUser.getJSONArray("requests");
            // 遍历所有【权限请求】，判断当前请求的地址是否在【权限请求】里
            for (int i = 0, l = requests.size(); i < l; i++) {
                String request = (String) requests.get(i);
                if (path.contains(request)) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                log.warn("权限校验未通过");
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            log.warn("权限校验通过");
            return chain.filter(exchange);
        }
    }


    /**
     * 过滤器顺序
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
