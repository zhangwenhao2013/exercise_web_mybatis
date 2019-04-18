package com.conan.service;

import com.conan.beans.Message;
import com.conan.dao.MessageDao;

import java.util.List;

public class ListService {

    public List<Message> queryMessageList(String command, String description) {

        MessageDao messageDao = new MessageDao();
        return messageDao.queryMessages(command, description);
    }
}
