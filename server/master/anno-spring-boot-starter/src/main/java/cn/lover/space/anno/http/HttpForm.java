package cn.lover.space.anno.http;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * date: 2020-03-29
 * time: 16:26
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpForm {

    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";

    HttpSpec spec() default @HttpSpec;
}
