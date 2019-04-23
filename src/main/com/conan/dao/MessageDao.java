package com.conan.dao;

import com.conan.beans.Command;
import com.conan.beans.Message;

import java.util.List;

/**
 * 这个dao 自从 使用接口替换 mybatis 的 namespace 之后就只起到隔离作用了
 */
public interface MessageDao {

    /**
     * 查询消息
     *
     * @param command     指令
     * @param description 描述
     * @return
     */
    List<Message> queryMessages(String command, String description);

    /**
     * 根据指令查询 对应的回复
     *
     * @param command
     * @return
     */
    List<Message> queryMessages(String command);

    /**
     * 删除单条message
     *
     * @param id messageid
     * @return
     */
    void deleteOneMessages(String id);

    void deleteBathcMessages(List<Integer> list);

    List<Command> queryCommands(String command);

    List<Command> queryCommands(String command, String description);
}
