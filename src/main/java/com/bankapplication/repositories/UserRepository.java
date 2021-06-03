package com.bankapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapplication.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
