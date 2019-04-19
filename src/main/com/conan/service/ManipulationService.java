package com.conan.service;

import com.conan.dao.MessageBatisDaoImpl;

import java.util.ArrayList;

public class ManipulationService {

    public void deleteOneMessage(String id) {

        if (id != null && !"".equals(id)) {
            MessageBatisDaoImpl batisDao = new MessageBatisDaoImpl();
            batisDao.deleteOneMessages(id);
        }
    }

    public void deleteBatchMessage(String[] ids) {

        if (ids == null) {
            return;
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (String id : ids) {
            try {
                Integer integer = Integer.valueOf(id);
                list.add(integer);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } finally {

            }
        }

//        List<String> list = Arrays.asList(ids);

        MessageBatisDaoImpl dao = new MessageBatisDaoImpl();
        dao.deleteBathcMessages(list);
    }
}
