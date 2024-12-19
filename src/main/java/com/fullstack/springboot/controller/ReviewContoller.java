package com.fullstack.springboot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.springboot.dto.MovieReviewDTO;
import com.fullstack.springboot.service.MovieReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
public class ReviewContoller {
	
	private final MovieReviewService movieReviewService;
	
	
	@GetMapping("/{mno}/all")
	public ResponseEntity<List<MovieReviewDTO>> getList(@PathVariable("mno") Long mno) {
		List<MovieReviewDTO> reviewDTOList = movieReviewService.getListOfMovie(mno);
		
		return ResponseEntity.ok(reviewDTOList);
	}
	
	@PostMapping("/{mno}")
	public ResponseEntity<Long> addReview(@RequestBody MovieReviewDTO movieReviewDTO) {
		Long reviewNum = movieReviewService.register(movieReviewDTO);
		return ResponseEntity.ok(reviewNum);
	}
	
	@PutMapping("/{mno}/{reviewNum}")
	public ResponseEntity<Long> modifyReview(@RequestBody MovieReviewDTO movieReviewDTO) {
			log.error("수정 요청 번호 "+movieReviewDTO.getMno()+" : "+movieReviewDTO.getReviewnum());
			
			movieReviewService.modify(movieReviewDTO);
		return ResponseEntity.ok(movieReviewDTO.getReviewnum());
	}
	
	@DeleteMapping("/delete/{reviewnum}")
	public ResponseEntity<Long> deleteReview(@PathVariable("reviewnum") Long reviewnum) {
		log.error("삭제 요청 번호 "+reviewnum);
		
		movieReviewService.remove(reviewnum);
	return ResponseEntity.ok(reviewnum);
}
}
