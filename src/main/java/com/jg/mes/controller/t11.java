package com.jg.mes.controller;

import com.jg.mes.dao.DeptDao;
import com.jg.mes.dao.UserDao;
import com.jg.mes.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class t11 {
    @Autowired
    private UserDao userdao;

    @Autowired
    private DeptDao deptdao;


    @GetMapping("/ppp")
    public List<Dept> pppa()
    {
        List <Dept> depts = deptdao.findAll();
        return depts;
    }
}
