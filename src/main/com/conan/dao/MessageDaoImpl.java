package com.conan.dao;

import com.conan.beans.Message;

import java.util.List;

public interface MessageDaoImpl {

    List<Message> queryMessages(String command, String description);
}
