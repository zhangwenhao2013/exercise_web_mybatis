package com.conan.dao;

import com.conan.beans.Message;

import java.util.List;

public interface MessageDaoImpl {

    /**
     * 查询消息
     *
     * @param command     指令
     * @param description 描述
     * @return
     */
    List<Message> queryMessages(String command, String description);

    /**
     * 删除单条message
     *
     * @param id messageid
     * @return
     */
    void deleteOneMessages(String id);

    void deleteBathcMessages(List<Integer> list);
}
