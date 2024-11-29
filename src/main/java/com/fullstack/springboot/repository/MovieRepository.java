package com.fullstack.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	//영화에 대한 평점과 리뷰갯수 등을 구하는 JPQL
	@Query("select m, avg(coalesce(r.grade,0)), count(distinct r) from Movie m left join MovieReview r on r.movie = m group by m ")
	Page<Object[]> getListPage(Pageable pageable);
	
	//영화에 대한 평점과 리뷰갯수, 대표이미지 등을 구하는 JPQL
//	@Query("select m, max(mi.imgName), avg(coalesce(r.grade,0)), count(distinct r) "
//	         + "from Movie m left join MovieReview r on r.movie = m "
//	         + "left join MovieImage mi on mi.movie = m "
//	         + "group by m.mno")
	@Query("select m, mi, avg(coalesce(r.grade,0)), count(distinct r) from Movie m "
			+ "left join MovieImage mi on mi.movie = m "
			+ "left join MovieReview r on r.movie = m group by m")
	Page<Object[]> getListPageWithImgs(Pageable pageable);
	
	//영화에 대한 상세 정보 확인시 영화와 영화 이미지를 get 하는 쿼리
	@Query("select m, mi, avg(coalesce(r.grade,0)), count(distinct r) "
			+ "from Movie m left join MovieImage mi on mi.movie = m "
	         + "left join MovieReview r on r.movie = m "
	         + "where m.mno = :mno "
	         + "group by mi")
	List<Object[]> getReadPageWithImgs(@Param("mno") Long mno);
	
	@Query("select m, mi, avg(coalesce(r.grade,0)), count(distinct (r)) from "
	         + "Movie m left join MovieImage mi on mi.movie = m "
	         + "left join MovieReview r on r.movie = m "
	         + "where m.mno = :mno group by mi")
	   List<Object[]> getMovieWithAll(@Param("mno") Long mno);
}
