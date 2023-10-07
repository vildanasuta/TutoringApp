package com.spring.common.interfaces;

import com.spring.dao.models.Tutee;

import java.util.List;
import java.util.Optional;

public interface TuteeService extends CrudService<Tutee,Long> {


    Optional<Tutee> findByEmail(String email);

}
