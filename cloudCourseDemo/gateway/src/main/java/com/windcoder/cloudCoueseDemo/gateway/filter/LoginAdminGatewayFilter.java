package com.windcoder.cloudCoueseDemo.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

@Component
@Slf4j
public class LoginAdminGatewayFilter implements GatewayFilter, Ordered {

    /**
     * 真正的实现
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取header的token参数
        String token = exchange.getRequest().getHeaders().getFirst("token");
        log.info("登录拦截开始，token：{}", token);
        return chain.filter(exchange);
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
