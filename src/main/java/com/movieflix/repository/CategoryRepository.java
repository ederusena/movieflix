package com.movieflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieflix.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
