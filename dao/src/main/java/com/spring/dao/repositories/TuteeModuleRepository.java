package com.spring.dao.repositories;

import com.spring.dao.models.Tutee;
import com.spring.dao.models.TuteeModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TuteeModuleRepository extends JpaRepository<TuteeModule, Long> {
    List<TuteeModule> findByTutee(Tutee tutee);

}
