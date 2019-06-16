package com.mooc.zbs.web.mvc;

import javax.lang.model.element.Element;
import java.lang.annotation.*;

/**
 * Created by HinTi on 2019/6/12.
 * Goal:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Controller {
}
