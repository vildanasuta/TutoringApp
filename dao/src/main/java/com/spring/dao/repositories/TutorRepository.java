package com.spring.dao.repositories;

import com.spring.dao.models.Tutee;
import com.spring.dao.models.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor, Long>{
    Optional<Tutor> findByLastName(String lastName);


    Optional<Tutor> findByEmail(String email);
    Optional<List<Tutor>> findByLastNameLike(String lastName);
}