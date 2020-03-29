package cn.lover.space.anno.resolver.http;

import cn.lover.space.anno.http.HttpSpec;
import cn.lover.space.anno.throwable.WebInputFormatterException;
import cn.lover.space.anno.throwable.WebInputNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * date: 2020-03-29
 * time: 16:33
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public abstract class BaseHttpArgumentResolver implements HandlerMethodArgumentResolver {

    public abstract Mono<MultiValueMap<String, String>> findParameterMap(ServerWebExchange exchange);

    public abstract String findParameterName(MethodParameter parameter);

    public abstract HttpSpec findHttpSpec(MethodParameter parameter);

    @Override
    public Mono<Object> resolveArgument(
            MethodParameter parameter,
            BindingContext bindingContext,
            ServerWebExchange serverWebExchange
    ) {
        String name = StringUtils.defaultString(findParameterName(parameter), parameter.getParameterName());
        return findParameterMap(serverWebExchange)
                .map(paramMap -> {
                    String value = paramMap.getFirst(name);
                    if (!StringUtils.isBlank(value)) {
                        return value;
                    }
                    HttpSpec spec = findHttpSpec(parameter);
                    if (spec.require()) {
                        throw new WebInputNotFoundException("Require parameter: " + name + " but not found.");
                    }
                    return spec.value();
                }).map(value -> {
                    try {
                        return ApplicationConversionService
                                .getSharedInstance()
                                .convert(value, parameter.getParameterType());
                    } catch (ConversionFailedException e) {
                        throw new WebInputFormatterException("Parameter: " + name + " format error.", e);
                    }
                });
    }
}
