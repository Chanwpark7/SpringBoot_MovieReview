package com.fullstack.springboot.service;

import java.util.List;

import com.fullstack.springboot.dto.MovieReviewDTO;
import com.fullstack.springboot.entity.Member;
import com.fullstack.springboot.entity.Movie;
import com.fullstack.springboot.entity.MovieReview;

public interface MovieReviewService {

	Long register(MovieReviewDTO movieReviewDTO);
	
	List<MovieReviewDTO> getListOfMovie(Long mno);
	void modify(MovieReviewDTO movieReviewDTO);
	void remove(Long reviewNum);
	
	
	default MovieReview dtoToEntity(MovieReviewDTO movieReviewDTO) {
		MovieReview movieReview = MovieReview.builder()
				.reviewnum(movieReviewDTO.getReviewnum())
				.movie(Movie.builder().mno(movieReviewDTO.getMno()).build())
				.member(Member.builder().mid(movieReviewDTO.getMid()).build())
				.text(movieReviewDTO.getText())
				.grade(movieReviewDTO.getGrade())
				.build();
		return movieReview;
	}
	
	default MovieReviewDTO entityToDTO(MovieReview movieReview) {
		MovieReviewDTO movieReviewDTO = MovieReviewDTO.builder()
				.reviewnum(movieReview.getReviewnum())
				.mno(movieReview.getMovie().getMno())
				.mid(movieReview.getMember().getMid())
				.nickname(movieReview.getMember().getNickname())
				.email(movieReview.getMember().getEmail())
				.grade(movieReview.getGrade())
				.text(movieReview.getText())
				.regDate(movieReview.getRegDate())
				.modDate(movieReview.getModDate())
				.build();
		return movieReviewDTO;
	}
}
