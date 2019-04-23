package com.conan.dao.newDao;

import com.conan.beans.Command;

import java.util.List;

/**
 * 这个接口是替换 mybatis namespace 的
 */
public interface MybatisMessageDao {

    List<Command> selectCommandList(String name);

    List<Command> selectCommandListByCommand(Command name);

    void deleteOneMessage(int id);

    void deleteBatchMessage(List<Integer> ids);


}
