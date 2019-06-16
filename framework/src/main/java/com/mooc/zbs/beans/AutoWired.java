package com.mooc.zbs.beans;

import java.lang.annotation.*;

/**
 * Created by HinTi on 2019/6/13.
 * Goal: 属性上，用于注入属性依赖
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoWired {
}
