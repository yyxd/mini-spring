package com.mooc.zbs.starter;

import com.mooc.zbs.beans.BeanFactory;
import com.mooc.zbs.core.ClassScanner;
import com.mooc.zbs.web.handler.HandlerManager;
import com.mooc.zbs.web.server.TomcatServer;
import org.apache.catalina.LifecycleException;

import java.io.IOException;
import java.util.List;

/**
 * Created by HinTi on 2019/6/12.
 * Goal:
 */
public class MiniApplication {
    //框架的入口类
    public static void run(Class<?> cls, String[] args){
        System.out.println("Hello mini-spring!");
        TomcatServer tomcatServer = new TomcatServer(args);
        try {
            tomcatServer.startServer();
            System.out.println(cls.getPackage().getName());
            List<Class<?>> classList = ClassScanner.scanClasses(cls.getPackage().getName());
            //bean 工厂初始化bean
            BeanFactory.initBean(classList);
            HandlerManager.resolveMappingHandler(classList);
            classList.forEach(it-> System.out.println(it.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
