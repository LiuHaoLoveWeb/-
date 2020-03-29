package cn.lover.space.anno.resolver.http;

import cn.lover.space.anno.http.ClientIP;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * date: 2020-03-29
 * time: 16:55
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public class ClientIPArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(ClientIP.class);
    }

    @Override
    public Mono<Object> resolveArgument(
            MethodParameter methodParameter,
            BindingContext bindingContext,
            ServerWebExchange serverWebExchange
    ) {
        return Mono.create(sink -> {
            ServerHttpRequest request = serverWebExchange.getRequest();
            HttpHeaders headers = request.getHeaders();
            String ip = headers.getFirst("X-Real-IP");
            if (StringUtils.hasText(ip)) {
                sink.success(ip);
                return;
            }
            ip = headers.getFirst("x-forwarded-for");
            if (StringUtils.hasText(ip)) {
                sink.success(ip);
                return;
            }
            ip = headers.getFirst("HTTP_X_FORWARDED_FOR");
            if (StringUtils.hasText(ip)) {
                sink.success(ip);
                return;
            }
            ip = request.getRemoteAddress().getAddress().getHostAddress();
            sink.success(StringUtils.hasText(ip) ? ip : "");
        });
    }
}
