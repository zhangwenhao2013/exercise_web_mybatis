package com.conan.proxytest;

import java.util.List;

public class ProxyTextMain {
    public static void main(String[] args) {
        SqlSession sqlSession = new SqlSession();
        IProxy mapper = sqlSession.getMapper(IProxy.class);
        List<String> query = mapper.query();
        for (String s : query) {
            System.out.println(s);
        }
    }
}
