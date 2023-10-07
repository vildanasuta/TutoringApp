package com.spring.dao.repositories;

import com.spring.dao.models.Tutee;
import com.spring.dao.models.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

public interface TuteeRepository extends JpaRepository<Tutee, Long> {

    Optional<Tutee> findByLastName(String lastName);

    Optional<Tutee> findByEmail(String email);
    Optional<List<Tutee>> findByLastNameLike(String lastName);

}
