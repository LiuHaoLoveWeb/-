package cn.lover.space.anno.http;

import java.lang.annotation.*;

/**
 * date: 2020-03-29
 * time: 16:23
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpSpec {

    String value() default "";

    boolean require() default false;
}
