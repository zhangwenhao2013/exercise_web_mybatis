package com.conan.service;

import com.conan.Consts;
import com.conan.beans.Message;
import com.conan.dao.MessageBatisDaoImpl;
import com.conan.dao.MessageDao;

import java.util.List;
import java.util.Random;

public class QueryService {

    public List<Message> queryMessageList(String command, String description) {

//        MessageDao messageDao = new MessageDaoImpl();
        MessageDao messageDao = new MessageBatisDaoImpl();
        return messageDao.queryMessages(command, description);
    }

    public String queryMessageList(String command) {

        MessageDao messageDao = new MessageBatisDaoImpl();
        if (Consts.CONSTS_HELP.equals(command)) {
            List<Message> list = messageDao.queryMessages("", "");
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < list.size(); i++) {
                if (i != 0) {
                    stringBuilder.append("<br/>");
                }
                stringBuilder.append("回复[" + list.get(i).getCommand() + "]可以查看" + list.get(i).getDescription());
            }
            return stringBuilder.toString();
        }

        List<Message> list = messageDao.queryMessages(command);

        if (list != null && list.size() > 0) {
            Random random = new Random();
            int i = random.nextInt(list.size());
            Message message = list.get(i);
            return message.getDescription();
        }
        return Consts.CONSTS_DEFAULT;
    }
}
