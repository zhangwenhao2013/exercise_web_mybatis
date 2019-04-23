package com.conan.proxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

public class SqlSession {

    public <T> T getMapper(Class<T> cls) {

        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                /**
                 * 这个相当于执行 sql 语句;无需管是否代理那个类;
                 */
                ArrayList<String> strings = new ArrayList<>();
                strings.add("a");
                strings.add("b");
                strings.add("c");
                return strings;
            }
        });
    }
}
