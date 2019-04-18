package com.conan;

import com.conan.beans.Message;
import com.conan.dao.MessageBatisDao;
import com.conan.dao.MessageDaoImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        MessageDaoImpl messageDao = new MessageBatisDao();
        List<Message> messages = messageDao.queryMessages("", "");
    }
}
