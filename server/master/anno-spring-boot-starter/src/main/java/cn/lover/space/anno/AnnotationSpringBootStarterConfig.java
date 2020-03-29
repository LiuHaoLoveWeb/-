package cn.lover.space.anno;

import cn.lover.space.anno.resolver.http.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

/**
 * date: 2020-03-29
 * time: 16:29
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
@Configuration
public class AnnotationSpringBootStarterConfig implements WebFluxConfigurer {

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        configurer.addCustomResolver(new HttpParamArgumentResolver());
        configurer.addCustomResolver(new HttpHeaderArgumentResolver());
        configurer.addCustomResolver(new HttpFormArgumentResolver());
        configurer.addCustomResolver(new UserAgentArgumentResolver());
        configurer.addCustomResolver(new ClientIPArgumentResolver());
    }
}
