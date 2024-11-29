package com.fullstack.springboot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstack.springboot.dto.MovieDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResultDTO;
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
	
	@Override
	public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

		Pageable pageable = pageRequestDTO.getPageable(Sort.by("mno").descending());
		
		Page<Object[]> result = movieRepository.getListPageWithImgs(pageable);
		
		Function<Object[], MovieDTO> fn = (objArr -> entityToDTO((Movie)objArr[0], (List<MovieImage>)(Arrays.asList((MovieImage)objArr[1])), (Double)objArr[2], (Long)objArr[3]));
		return new PageResultDTO(result, fn);
	}
	
	@Override
	public MovieDTO getMovie(Long mno) {

		List<Object[]> result = movieRepository.getMovieWithAll(mno);
		
		Movie movie = (Movie)result.get(0)[0];
		
		//영화의 이미지도 get 해야하니 갯수만큼 루프
		List<MovieImage> movieImages = new ArrayList<MovieImage>();
		
		result.forEach(arr -> {
			MovieImage movieImage = (MovieImage)arr[1];
			
			movieImages.add(movieImage);
		});
		
		Double avg = (Double)result.get(0)[2];
		Long reviewCnt = (Long)result.get(0)[3];
		
		return entityToDTO(movie, movieImages, avg, reviewCnt);
	}
}
