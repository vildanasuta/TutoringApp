package com.spring.common.interfaces;

import com.spring.dao.models.Tutee;
import com.spring.dao.models.TuteeModule;

import java.util.List;

public interface TuteeModuleService  extends CrudService<TuteeModule,Long>{
    List<TuteeModule> findByTutee(Tutee tutee);
}
