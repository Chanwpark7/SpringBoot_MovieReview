package com.fullstack.springboot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.MovieReviewDTO;
import com.fullstack.springboot.entity.Movie;
import com.fullstack.springboot.entity.MovieReview;
import com.fullstack.springboot.repository.MovieReviewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieReviewServiceImpl implements MovieReviewService {

	private final MovieReviewRepository movieReviewRepository;
	
	@Override
	public Long register(MovieReviewDTO movieReviewDTO) {
		MovieReview movieReview = dtoToEntity(movieReviewDTO);
		movieReviewRepository.save(movieReview);
		return movieReview.getReviewnum();
	}

	@Override
	public List<MovieReviewDTO> getListOfMovie(Long mno) {
		Movie movie = Movie.builder().mno(mno).build();
		List<MovieReview> result = movieReviewRepository.findByMovie(movie);
		return result.stream().map(movieReview -> entityToDTO(movieReview)).collect(Collectors.toList());
	}

	@Override
	public void modify(MovieReviewDTO movieReviewDTO) {
		Optional<MovieReview> result = movieReviewRepository.findById(movieReviewDTO.getReviewnum());
		if(result.isPresent()) {
			MovieReview review = result.get();
			
			review.changeGrade(movieReviewDTO.getGrade());
			review.changeText(movieReviewDTO.getText());
			
			movieReviewRepository.save(review);
		}
	}

	@Override
	public void remove(Long reviewNum) {

		movieReviewRepository.deleteById(reviewNum);
	}

}
