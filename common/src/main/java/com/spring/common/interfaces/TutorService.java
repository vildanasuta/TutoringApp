package com.spring.common.interfaces;

import com.spring.dao.models.Tutee;
import com.spring.dao.models.Tutor;

import java.util.List;
import java.util.Optional;

public interface TutorService extends CrudService<Tutor,Long> {

        Optional<Tutor> findByEmail(String email);



}