package com.spring.dao.repositories;

import com.spring.dao.models.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    Module findByName(String name);
    List<Module> findByNameLike(String name);


}
