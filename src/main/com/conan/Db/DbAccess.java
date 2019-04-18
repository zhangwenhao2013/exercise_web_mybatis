package com.conan.Db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;


public class DbAccess {

    /**
     * 使用 mybatis 获取sqlsession
     *
     * @return
     * @throws IOException
     */
    public static SqlSession getSqlSession() throws IOException {
        //读取配置信息
        Reader reader = Resources.getResourceAsReader("com/conan/config/mybatis-config.xml");

        //构建sqlsessionfactory
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

        //获取sqlsession
        SqlSession sqlSession = sessionFactory.openSession();
        return sqlSession;
    }

    public static void closeSqlSession(Closeable sqlSession) {
        if (sqlSession != null) {
            try {
                sqlSession.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
