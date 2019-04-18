package com.conan.dao;

import com.conan.Db.DbAccess;
import com.conan.beans.Message;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao 执行sql 获取操作结果  封装结果返回
 * <p>
 * 获取连接,加载驱动(不是DAO的工作)
 */
public class MessageBatisDao implements MessageDaoImpl {

    @Override
    public List<Message> queryMessages(String command, String description) {
        SqlSession sqlSession = null;
        try {
            sqlSession = DbAccess.getSqlSession();
            return null;
//            sqlSession.select();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DbAccess.closeSqlSession(sqlSession);
        }
        return null;
    }
}
