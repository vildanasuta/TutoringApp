package com.spring.common.interfaces;

import com.spring.dao.models.Module;

import java.util.List;

public interface ModuleService extends CrudService<Module,Long> {

        Module findByName(String name);

        List<Module> findByNameLike(String name);

}