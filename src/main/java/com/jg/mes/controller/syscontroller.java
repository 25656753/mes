package com.jg.mes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jg.mes.Anno.TargetDataSource;
import com.jg.mes.dao.DeptDao;
import com.jg.mes.dao.ModuleDao;
import com.jg.mes.dao.UserDao;
import com.jg.mes.entities.Module;
import com.jg.mes.entities.User;
import com.jg.mes.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@Transactional
public class syscontroller {
    final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserDao userdao;

    @Autowired
    private DeptDao deptdao;

    @Autowired
    private ModuleDao moduleDao;

    @Autowired
    private DeptService deptService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/")

    public String home() {
       /* Dept dept = new Dept();
        dept.setDeptname("工程部");
        deptdao.save(dept);
        Module module = new Module();
        module.setMname("aa");
        Set<User> users = new HashSet<>();


        User user = new User("jam", new Date(), dept);
        users.add(user);
        deptdao.save(dept);
        User user1 = new User("张三古", new Date(), dept);
        users.add(user1);

        module.setUsers(users);


        userdao.save(user);
        userdao.save(user1);
        moduleDao.save(module);*/
       // Pageable pageable=PageRequest.of(0,2);
       // Page<User> users=userdao.findAll(pageable);

        AtomicReference<Thread>  tt=new AtomicReference<>();
        ThreadPoolExecutor pool=new ThreadPoolExecutor(2,3,5L,
                TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
                List < User > users = userdao.inall();
        logger.error(users.size()+"");
        userdao.ina("402883346e43d7d6016e43f73e940003","aa");
        return "sys/list1";
    }

    @Autowired
    DataSource dataSource;

    @RequestMapping("/aa")
    public  String tg(ModelMap map)
    {
        map.put("c","#443454");
        map.put("a","54ggg");
        return "sys/list1";
    }

    @GetMapping("/t")
    @TargetDataSource(name = "test3")
    @ResponseBody
    public Set<Module> aa(ModelMap map) {
        List<User> p = userdao.findAll();
        logger.info("datasource--->" + dataSource.getClass().getName());
        return p.get(0).getModels();
    }

}
