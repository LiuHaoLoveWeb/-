package cn.lover.space.anno.resolver.http;

import cn.lover.space.anno.http.HttpForm;
import cn.lover.space.anno.http.HttpSpec;
import org.springframework.core.MethodParameter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * date: 2020-03-29
 * time: 16:47
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public class HttpFormArgumentResolver extends BaseHttpArgumentResolver {
    @Override
    public Mono<MultiValueMap<String, String>> findParameterMap(ServerWebExchange exchange) {
        return exchange.getFormData();
    }

    @Override
    public String findParameterName(MethodParameter parameter) {
        return parameter.getParameterAnnotation(HttpForm.class).name();
    }

    @Override
    public HttpSpec findHttpSpec(MethodParameter parameter) {
        return parameter.getParameterAnnotation(HttpForm.class).spec();
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(HttpForm.class);
    }
}
