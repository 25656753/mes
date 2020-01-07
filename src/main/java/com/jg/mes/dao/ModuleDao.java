package com.jg.mes.dao;

import com.jg.mes.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleDao extends JpaRepository<Module, Long> {
}
