package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
