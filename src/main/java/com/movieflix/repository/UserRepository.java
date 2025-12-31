package com.movieflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieflix.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
