package com.jg.mes.service;

import com.jg.mes.dao.DeptDao;
import com.jg.mes.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DeptService {
    @Autowired
    private DeptDao deptDao;


    public List<Dept> getall() {
        return deptDao.findAll();
    }


}
