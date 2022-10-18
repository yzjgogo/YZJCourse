package zhl.common.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * op配置，配置在 PoCOpManager中的请求id中
 *
 * @author zqs
 * @createTime 2015年1月15日 下午5:38:06
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiClass {
    Class<? extends BaseApi> ApiClassName() default BaseApi.class;
}