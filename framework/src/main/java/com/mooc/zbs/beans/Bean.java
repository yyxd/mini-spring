package com.mooc.zbs.beans;

import java.lang.annotation.*;

/**
 * Created by HinTi on 2019/6/13.
 * Goal: 注解，一个类可以解析为bean
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Bean {
}
