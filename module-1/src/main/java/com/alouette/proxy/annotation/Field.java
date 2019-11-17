package com.alouette.proxy.annotation;

import java.lang.annotation.*;

/**
 * Created by zm on 2019/11/17.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
public @interface Field {

    String fieldName() default "";

    String type() default "";


}
