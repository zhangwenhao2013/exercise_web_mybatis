package com.conan.dao;

import com.conan.Db.DbAccess;
import com.conan.beans.Message;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Dao 执行sql 获取操作结果  封装结果返回
 */
public class MessageBatisDao implements MessageDaoImpl {

    @Override
    public List<Message> queryMessages(String command, String description) {
        SqlSession sqlSession = null;
        List<Message> queryMessages = null;
        try {
            sqlSession = DbAccess.getSqlSession();
            Message message = new Message();
            message.setCommand(command);
            message.setDescription(description);

            HashMap<String, String> paramMap = new HashMap<>();
            paramMap.put("command", command);
            paramMap.put("description", description);

//            queryMessages = sqlSession.selectList("Message.queryMessages", message);
            queryMessages = sqlSession.selectList("Message.queryMessagesWithMapParams", paramMap);
            return queryMessages;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DbAccess.closeSqlSession(sqlSession);
        }
        return queryMessages;
    }

    @Override
    public void deleteOneMessages(String id) {

        SqlSession sqlSession = null;
        try {
            sqlSession = DbAccess.getSqlSession();
            int idInt = Integer.valueOf(id);
            sqlSession.delete("Message.deleteOneMessage", idInt);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DbAccess.closeSqlSession(sqlSession);
        }
    }

    @Override
    public void deleteBathcMessages(List<Integer> list) {
        SqlSession sqlSession = null;
        try {
            sqlSession = DbAccess.getSqlSession();
            int delete = sqlSession.delete("Message.deleteBatchMessage", list);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DbAccess.closeSqlSession(sqlSession);
        }

    }
}
