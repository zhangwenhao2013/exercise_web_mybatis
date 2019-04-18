package com.conan.dao;

import com.conan.beans.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {

    public List<Message> queryMessages(String command, String description) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = null;

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/micro_message", "root", "12345678");

            String sql = "select id,command,description , content from message where  1=1";

            StringBuilder stringBuilder = new StringBuilder(sql);

            ArrayList<String> paramList = new ArrayList<>();
            if (command != null && "" != command.trim()) {
                stringBuilder.append(" and command = ?");
                paramList.add(command);
            }

            if (description != null && "" != description.trim()) {
                stringBuilder.append(" and description = ?");
                paramList.add(description);
            }

            PreparedStatement statement = connection.prepareStatement(stringBuilder.toString());
            for (int i = 0; i < paramList.size(); i++) {
                statement.setString(i + 1, paramList.get(i));
            }

            ResultSet resultSet = statement.executeQuery();

            List<Message> messageList = new ArrayList<>();
            while (resultSet.next()) {
                Message message = new Message();

                message.setId(resultSet.getString("id"));
                message.setCommand(resultSet.getString("command"));
                message.setDescription(resultSet.getString("description"));
                message.setContent(resultSet.getString("content"));

                messageList.add(message);
            }

            return messageList;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
