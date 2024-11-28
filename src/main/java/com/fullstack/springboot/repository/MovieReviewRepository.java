package com.fullstack.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.entity.Movie;
import com.fullstack.springboot.entity.MovieReview;

public interface MovieReviewRepository extends JpaRepository<MovieReview, Long> {

	@Query("select r, m, mb "
			+ "from MovieReview r left join Movie m "
			+ "on r.movie = m "
			+ "left join Member mb "
			+ "on r.member = mb "
			+ "where m.mno = :mno "
			+ "group by r")
	Object[] getReadByMno(@Param("mno") Long mno);
	
	List<MovieReview> findByMovie(Movie movie);
}
