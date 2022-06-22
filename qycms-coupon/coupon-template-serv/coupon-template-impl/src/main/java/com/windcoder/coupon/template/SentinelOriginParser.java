package com.windcoder.coupon.template;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class SentinelOriginParser   implements RequestOriginParser {

    /**
     * 在 Template 服务中识别来自上游的标记，并将其加入到 Sentinel 的链路统计中
     *
     * 借助 Sentinel 提供的 RequestOriginParser 扩展接口，编写一个自定义的解析器
     * @param request
     * @return
     */
    @Override
    public String parseOrigin(HttpServletRequest request) {
        log.info("request {}, header={}", request.getParameterMap(), request.getHeaderNames());
        return request.getHeader("SentinelSource");
    }
}
