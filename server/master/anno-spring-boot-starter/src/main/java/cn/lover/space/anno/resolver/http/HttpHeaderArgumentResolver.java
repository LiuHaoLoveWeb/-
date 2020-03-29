package cn.lover.space.anno.resolver.http;

import cn.lover.space.anno.http.HttpHeader;
import cn.lover.space.anno.http.HttpSpec;
import org.springframework.core.MethodParameter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * date: 2020-03-29
 * time: 16:46
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public class HttpHeaderArgumentResolver extends BaseHttpArgumentResolver {
    @Override
    public Mono<MultiValueMap<String, String>> findParameterMap(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getHeaders());
    }

    @Override
    public String findParameterName(MethodParameter parameter) {
        return parameter.getParameterAnnotation(HttpHeader.class).name();
    }

    @Override
    public HttpSpec findHttpSpec(MethodParameter parameter) {
        return parameter.getParameterAnnotation(HttpHeader.class).spec();
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(HttpHeader.class);
    }
}
