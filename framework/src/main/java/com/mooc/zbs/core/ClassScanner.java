package com.mooc.zbs.core;

import org.apache.tomcat.Jar;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by HinTi on 2019/6/12.
 * Goal: tools method
 */
public class ClassScanner {
    public static List<Class<?>> scanClasses(String packageName) throws IOException ,ClassNotFoundException{
        List<Class<?>> classList = new ArrayList<>();
        String path = packageName.replace(".","/");
        //classloader
        String filePath = "";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);
        while (resources.hasMoreElements()){
            URL resource = resources.nextElement();
            System.out.println(resource.getProtocol());
            if (resource.getProtocol().contains("jar")){
                JarURLConnection jarURLConnection = (JarURLConnection)resource.openConnection();
                String jarFilePath = jarURLConnection.getJarFile().getName();
                classList.addAll(getClassesFromJar(jarFilePath,path));
            }else {
                //todo jar others
            }
        }
        return classList;
    }

    private static List<Class<?>> getClassesFromJar(String jarFilePath, String path) throws IOException,ClassNotFoundException{
        List<Class<?>> classes = new ArrayList<>();
        JarFile jarFile = new JarFile(jarFilePath);
        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()){
            JarEntry jarEntry = jarEntries.nextElement();
            String entryName = jarEntry.getName();
            if(entryName.startsWith(path)&&entryName.endsWith(".class")){
                String classFullName = entryName.replace("/",".").substring(0,entryName.length()-6);
                classes.add(Class.forName(classFullName));
            }
        }
        return classes;
    }
}
