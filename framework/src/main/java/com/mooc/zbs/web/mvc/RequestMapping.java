package com.mooc.zbs.web.mvc;

import java.lang.annotation.*;

/**
 * Created by HinTi on 2019/6/12.
 * Goal:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping {
    String value();
}
