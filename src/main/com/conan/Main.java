package com.conan;

import com.conan.beans.Message;
import com.conan.dao.MessageBatisDao;
import com.conan.dao.MessageDaoImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        MessageDaoImpl messageDao = new MessageBatisDao();
//        messageDao.deleteOneMessages("6");
        List<Integer> integers = new ArrayList();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        messageDao.deleteBathcMessages(integers);
        List<Message> messages = messageDao.queryMessages("", "");
    }
}
