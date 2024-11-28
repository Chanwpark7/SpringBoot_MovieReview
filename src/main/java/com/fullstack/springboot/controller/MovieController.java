package com.fullstack.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fullstack.springboot.dto.MovieDTO;
import com.fullstack.springboot.dto.MovieImageDTO;
import com.fullstack.springboot.service.MovieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class MovieController {
	
	private final MovieService movieService;
	
	@GetMapping("/register")
	public void register() {
		//장난 쳐놓고 갈게
		// 	ㄴ누구냐
	}
	
	@PostMapping("/register")
	public void register(MovieDTO movieDTO) {
		log.error("등록처리 수행됨.");
		
		movieService.register(movieDTO);
	}
	
}
