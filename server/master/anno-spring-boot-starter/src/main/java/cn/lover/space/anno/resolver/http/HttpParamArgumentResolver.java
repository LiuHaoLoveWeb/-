package cn.lover.space.anno.resolver.http;

import cn.lover.space.anno.http.HttpParam;
import cn.lover.space.anno.http.HttpSpec;
import org.springframework.core.MethodParameter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * date: 2020-03-29
 * time: 16:44
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public class HttpParamArgumentResolver extends BaseHttpArgumentResolver {
    @Override
    public Mono<MultiValueMap<String, String>> findParameterMap(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getQueryParams());
    }

    @Override
    public String findParameterName(MethodParameter parameter) {
        return parameter.getParameterAnnotation(HttpParam.class).name();
    }

    @Override
    public HttpSpec findHttpSpec(MethodParameter parameter) {
        return parameter.getParameterAnnotation(HttpParam.class).spec();
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(HttpParam.class);
    }
}
