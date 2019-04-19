package com.conan.service;

import com.conan.dao.MessageBatisDao;

public class ManipulationService {

    public void deleteOneMessage(String id) {

        if (id != null && !"".equals(id)) {
            MessageBatisDao batisDao = new MessageBatisDao();
            batisDao.deleteOneMessages(id);
        }
    }
}
