package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.MovieReview;

public interface MovieReviewRepository extends JpaRepository<MovieReview, Long> {

}
