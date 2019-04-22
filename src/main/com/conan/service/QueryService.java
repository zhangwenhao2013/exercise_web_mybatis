package com.conan.service;

import com.conan.Consts;
import com.conan.beans.Command;
import com.conan.beans.CommandContent;
import com.conan.beans.Message;
import com.conan.dao.MessageBatisDaoImpl;
import com.conan.dao.MessageDao;

import java.util.List;
import java.util.Random;

public class QueryService {

    public List<Command> queryMessageList(String command, String description) {

//        MessageDao messageDao = new MessageDaoImpl();
        MessageDao messageDao = new MessageBatisDaoImpl();
        return messageDao.queryCommands(command, description);
    }

    public String queryMessageList(String command) {

        MessageDao messageDao = new MessageBatisDaoImpl();
        if (Consts.CONSTS_HELP.equals(command)) {

//            List<Message> list = messageDao.queryMessages("", "");
            List<Command> list = messageDao.queryCommands("", "");

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < list.size(); i++) {
                if (i != 0) {
                    stringBuilder.append("<br/>");
                }
                stringBuilder.append("回复[" + list.get(i).getName() + "]可以查看" + list.get(i).getDescription());
            }
            return stringBuilder.toString();
        }

        List<Command> list = messageDao.queryCommands(command);

        if (list != null && list.size() > 0) {
            Random random = new Random();
            int i = random.nextInt(list.size());
            Command message = list.get(i);

            List<CommandContent> contentList = message.getContentList();
            if (contentList != null && contentList.size() > 0) {
                Random random2 = new Random();
                int i2 = random2.nextInt(contentList.size());
                CommandContent commandContent = contentList.get(i2);
                return commandContent.getContent();
            }

        }
        return Consts.CONSTS_DEFAULT;
    }
}
