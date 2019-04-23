package com.conan.dao;

import com.conan.Db.DbAccess;
import com.conan.beans.Command;
import com.conan.beans.Message;
import com.conan.dao.newDao.MybatisMessageDao;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Dao 执行sql 获取操作结果  封装结果返回
 */
public class MessageBatisDaoImpl implements MessageDao {

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
    public List<Message> queryMessages(String command) {

        SqlSession sqlSession = null;
        List<Message> list = null;
        try {
            sqlSession = DbAccess.getSqlSession();
            list = sqlSession.selectList("Message.queryMessageByCommand", command);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DbAccess.closeSqlSession(sqlSession);
        }
        return null;
    }

    @Override
    public void deleteOneMessages(String id) {

        SqlSession sqlSession = null;
        try {
            sqlSession = DbAccess.getSqlSession();
            int idInt = Integer.valueOf(id);
            MybatisMessageDao mapper = sqlSession.getMapper(MybatisMessageDao.class);
            mapper.deleteOneMessage(idInt);
//            sqlSession.delete("Command.deleteOneMessage", idInt);
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
            MybatisMessageDao mapper = sqlSession.getMapper(MybatisMessageDao.class);
            mapper.deleteBatchMessage(list);
//            int delete = sqlSession.delete("Command.deleteBatchMessage", list);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DbAccess.closeSqlSession(sqlSession);
        }

    }

    @Override
    public List<Command> queryCommands(String command) {
        SqlSession sqlSession = null;
        List<Command> list = null;
        try {
            sqlSession = DbAccess.getSqlSession();
//            list = sqlSession.selectList("Command.selectCommandList", command);
            MybatisMessageDao mapper = sqlSession.getMapper(MybatisMessageDao.class);
            list = mapper.selectCommandList(command);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DbAccess.closeSqlSession(sqlSession);
        }
        return null;
    }

    @Override
    public List<Command> queryCommands(String command, String description) {
        SqlSession sqlSession = null;
        List<Command> list = null;
        try {
            sqlSession = DbAccess.getSqlSession();
            Command com = new Command();
            com.setName(command);
            com.setDescription(description);

            MybatisMessageDao mapper = sqlSession.getMapper(MybatisMessageDao.class);
            list = mapper.selectCommandListByCommand(com);

//            list = sqlSession.selectList("Command.selectCommandListByCommand", com);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DbAccess.closeSqlSession(sqlSession);
        }
        return null;
    }
}
