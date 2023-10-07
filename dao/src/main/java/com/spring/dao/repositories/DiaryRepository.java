package com.spring.dao.repositories;

import com.spring.dao.models.Diary;
import com.spring.dao.models.Tutee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
        Diary findByTitle(String title);
        List<Diary> findByTitleLike(String title);

        // Declare the findByUsername method
        @Query("SELECT d FROM Diary d JOIN d.tutee t WHERE t.email = :email")

        Diary findByUsername(String email);

        Optional<Diary> findByTutee(Tutee tutee);
}
