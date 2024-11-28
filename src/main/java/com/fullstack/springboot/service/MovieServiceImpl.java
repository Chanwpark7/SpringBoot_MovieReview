package com.fullstack.springboot.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstack.springboot.dto.MovieDTO;
import com.fullstack.springboot.entity.Movie;
import com.fullstack.springboot.entity.MovieImage;
import com.fullstack.springboot.repository.MovieImageRepository;
import com.fullstack.springboot.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private final MovieImageRepository movieImageRepository; 
	
	@Autowired
	private final MovieRepository movieRepository;
	
	@Transactional
	@Override
	public Long register(MovieDTO movieDTO) {
		
		Map<String, Object> entityMap = dtoToEntity(movieDTO);
		Movie movie = (Movie)entityMap.get("movie");
		
		movieRepository.save(movie);
		//if(entityMap.containsKey("imgList")) {
			List<MovieImage> movieImages = (List<MovieImage>) entityMap.get("imgList");
			movieImages.forEach(movieImg->{
				movieImageRepository.save(movieImg);
			});
		//}
		
		return movie.getMno();
	}
}
