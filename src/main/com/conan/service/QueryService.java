package com.conan.service;

import com.conan.beans.Message;
import com.conan.dao.MessageBatisDaoImpl;
import com.conan.dao.MessageDao;

import java.util.List;

public class QueryService {

    public List<Message> queryMessageList(String command, String description) {
        //
//        MessageDao messageDao = new MessageDaoImpl();
        MessageDao messageDao = new MessageBatisDaoImpl();
        return messageDao.queryMessages(command, description);
    }
}
