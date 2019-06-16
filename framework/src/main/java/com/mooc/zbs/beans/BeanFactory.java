package com.mooc.zbs.beans;

import com.mooc.zbs.web.mvc.Controller;
import java.lang.reflect.Field;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by HinTi on 2019/6/13.
 * Goal: bean 工厂
 */
public class BeanFactory {
    private static Map<Class<?>,Object> classToBean = new ConcurrentHashMap<>();
    public static Object getBean(Class<?> cls){
        return classToBean.get(cls);
    }

    /**
     * 传入类定义初始化bean
     * @param classList
     */
    public static void initBean(List<Class<?>> classList) throws Exception{
        List<Class<?>> toCreate = new ArrayList<>(classList);
        while (toCreate.size()!=0){
            int remainSize = toCreate.size();
            for(int i=0;i<toCreate.size();i++){
                if(finishCreate(toCreate.get(i))){
                    toCreate.remove(i);
                }
            }
            // 存在循环依赖，抛出异常
            if(toCreate.size() == remainSize){
                throw new Exception("cycle dependency");
            }
            classToBean.forEach(
                (Key,value)-> System.out.println("key: "+Key.getName())
            );
        }

    }

    public static boolean finishCreate(Class<?> cls) throws IllegalAccessException, InstantiationException {
       //controller也是一种特殊的Bean
        if(!cls.isAnnotationPresent(Bean.class)&&!cls.isAnnotationPresent(Controller.class)) {
            return true;
        }
        Object bean = cls.newInstance();
        for(Field field:cls.getDeclaredFields()){
            if(field.isAnnotationPresent(AutoWired.class)){
                Class<?> fieldType =field.getType();
                Object reliantBean = BeanFactory.getBean(fieldType);
                if(reliantBean == null){
                    return false;
                }
                // 注入属性
                field.setAccessible(true);
                field.set(bean,reliantBean);
            }
        }
        classToBean.put(cls,bean);
        return true;
    }
}
