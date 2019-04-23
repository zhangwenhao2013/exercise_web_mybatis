package com.conan;

import com.conan.beans.Command;
import com.conan.beans.Message;
import com.conan.dao.MessageBatisDaoImpl;
import com.conan.dao.MessageDao;
import com.conan.proxy.UserService;
import com.conan.proxy.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        MessageDao messageDao = new MessageBatisDaoImpl();
//        messageDao.deleteOneMessages("6");
      /*  List<Integer> integers = new ArrayList();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        messageDao.deleteBathcMessages(integers);
        List<Message> messages = messageDao.queryMessages("", "");*/

//        List<Command> commands = messageDao.queryCommands("段子", null);
//        List<Command> commands = messageDao.queryCommands(null);
//        System.out.println(commands);


        final UserService userService = new UserServiceImpl();
        //创建一个InvocationHandler，描述我们希望代理者执行哪些操作
        InvocationHandler invocationHandler = new MyInvocationHandler(userService);
        //通过刚才创建的InvocationHandler，创建真正的代理者。第一个参数是类加载器，第二个参数是这个代理者实现哪些接口(与被代理者实现的是相同的接口)
//        UserService userServiceProxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),
//                userService.getClass().getInterfaces(), invocationHandler);

        UserService userServiceProxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                new Class[]{UserService.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object invoke = method.invoke(userService, args);
                        return invoke;
                    }
                });

//        System.out.println(userServiceProxy.getName(1));
//        System.out.println(userServiceProxy.getAge(1));

        userServiceProxy.print("xxxx");
//        userServiceProxy.print();

    }

    public static class MyInvocationHandler implements InvocationHandler {
        private Object target; //们既然要做代理，我们必须知道我们是给谁做代理，这里的obj就是被代理者。

        MyInvocationHandler() {
//            super();
        }

        MyInvocationHandler(Object target) {
//            super();
            this.target = target;
        }

        //然后是invoke的三个参数、第一个参数就是代理者，如果你想对代理者做一些操作可以使用这个参数；第二个就是被执行的方法，第三个是执行该方法所需的参数。
        @Override
        public Object invoke(Object o, Method method, Object[] args) throws Throwable {  //把我们想要通过代理者给被代理者追加的操作都写在invoke方法里面
            if ("getName".equals(method.getName())) {
                System.out.println("++++++before " + method.getName() + "++++++");
                Object result = method.invoke(target, args);
                System.out.println("++++++after " + method.getName() + "++++++");
                return result;
            } else {
                Object result = method.invoke(target, args);
                return result;
            }

        }
    }
}
