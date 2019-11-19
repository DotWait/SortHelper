package com.dotwait.algorithmic_practice.util;

import com.dotwait.algorithmic_practice.domain.StringJavaObject;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DynamicCompilation {
    public static Class compile(String sourceStr, String clsName, String methodName) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        //Java标准文件管理器
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        //Java文件对象
        JavaFileObject jfo = new StringJavaObject(clsName, sourceStr);
        //编译参数
        List<String> options = new ArrayList<>();
        //编译文件的存放地方
        options.addAll(Arrays.asList(new String[]{"-d","./"}));
        //要编译的单元
        List<JavaFileObject> jfos = Arrays.asList(new JavaFileObject[]{jfo});
        //设置编译环境
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, options, null, jfos);
        //编译成功
        if (task.call()){
            MyClassLoader classLoader = new MyClassLoader();
            return classLoader.findClass(clsName);
        }
        return null;
    }
}
