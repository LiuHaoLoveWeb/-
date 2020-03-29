package cn.lover.space.anno.resolver.http;

import cn.lover.space.anno.http.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * date: 2020-03-29
 * time: 16:51
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public class UserAgentArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(UserAgent.class);
    }

    @Override
    public Mono<Object> resolveArgument(
            MethodParameter methodParameter,
            BindingContext bindingContext,
            ServerWebExchange serverWebExchange
    ) {
        return Mono.create(sink -> {
            String userAgent = serverWebExchange.getRequest().getHeaders().getFirst("User-Agent");
            sink.success(StringUtils.defaultString(userAgent));
        });
    }
}
