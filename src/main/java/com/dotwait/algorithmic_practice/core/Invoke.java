package com.dotwait.algorithmic_practice.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Invoke {
    public static void invokeSort(Class aClass, String methodName, int[] array) throws Exception {
        //生成对象
        Object obj = aClass.newInstance();
        //调用方法
        Method method = aClass.getMethod(methodName, int[].class);
        method.invoke(obj, array);
    }
}
