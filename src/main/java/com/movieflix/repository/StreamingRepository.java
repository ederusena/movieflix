package com.movieflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieflix.model.Streaming;

@Repository
public interface StreamingRepository extends JpaRepository<Streaming, Long> {

}
