package com.jg.mes;

import com.jg.mes.Datasource.DynamicDataSourceRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@SpringBootApplication
@Import(DynamicDataSourceRegister.class)
public class MesApplication {
    @Autowired
    DataSource dataSource;


    public static void main(String[] args) {
        SpringApplication.run(MesApplication.class, args);
    }

    @PostConstruct
    public void init() {
        System.out.println("DATASOURCE = " + dataSource.getClass().getName());
    }
}
