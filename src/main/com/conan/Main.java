package com.conan;

import com.conan.beans.Command;
import com.conan.beans.Message;
import com.conan.dao.MessageBatisDaoImpl;
import com.conan.dao.MessageDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        MessageDao messageDao = new MessageBatisDaoImpl();
//        messageDao.deleteOneMessages("6");
      /*  List<Integer> integers = new ArrayList();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        messageDao.deleteBathcMessages(integers);
        List<Message> messages = messageDao.queryMessages("", "");*/

//        List<Command> commands = messageDao.queryCommands("段子", null);
        List<Command> commands = messageDao.queryCommands(null);
        System.out.println(commands);

    }
}
