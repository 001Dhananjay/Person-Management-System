package com.example.pm.repository;

import com.example.pm.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
    @EntityGraph(attributePaths = {"company"})
    Page<Person> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"company", "addresses"})
    Optional<Person> findById(Long id);
}
