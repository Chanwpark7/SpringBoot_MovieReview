package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.MovieImage;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {

}
