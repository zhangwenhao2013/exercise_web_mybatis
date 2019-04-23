package com.conan.proxy;

public class UserServiceImpl implements UserService {

    @Override
    public String getName(int id) {
        System.out.println("------getName------");
        return "Tom";
    }

    @Override
    public Integer getAge(int id) {
        System.out.println("------getAge------");
        return 10;
    }

    @Override
    public void print(String str) {
        System.out.println(str);
    }

    @Override
    public void print() {
        System.out.println("------print------");
    }
}
