package com.conan.service;

import com.conan.beans.Message;
import com.conan.dao.MessageBatisDao;
import com.conan.dao.MessageDao;
import com.conan.dao.MessageDaoImpl;

import java.util.List;

public class ListService {

    public List<Message> queryMessageList(String command, String description) {
        //
//        MessageDaoImpl messageDao = new MessageDao();
        MessageDaoImpl messageDao = new MessageBatisDao();
        return messageDao.queryMessages(command, description);
    }
}
