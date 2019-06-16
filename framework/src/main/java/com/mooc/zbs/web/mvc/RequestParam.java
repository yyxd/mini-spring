package com.mooc.zbs.web.mvc;

import java.lang.annotation.*;

/**
 * Created by HinTi on 2019/6/12.
 * Goal:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface RequestParam {
    String value();
}
