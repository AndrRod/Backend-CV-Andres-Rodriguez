package com.cvitae.projectcv.repository;

import com.cvitae.projectcv.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    public Person findByEmail(String email);
    public Boolean existsByEmail(String email);
}
