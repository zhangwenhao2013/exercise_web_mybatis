package com.conan.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        System.out.println("contextInitialized 尝试在执行驱动数据库");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        System.out.println("contextDestroyed 尝试在执行驱动数据库");
    }
}
