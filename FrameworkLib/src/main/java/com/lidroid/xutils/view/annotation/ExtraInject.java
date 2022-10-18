package com.lidroid.xutils.view.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 获取bundle传入参数
 *
 * @author ylf
 * @createTime 2014-5-23 下午2:18:49
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExtraInject {

    String value() default "";
}
