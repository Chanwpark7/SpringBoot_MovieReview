package com.fullstack.springboot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fullstack.springboot.dto.MovieDTO;
import com.fullstack.springboot.dto.MovieImageDTO;
import com.fullstack.springboot.entity.Movie;
import com.fullstack.springboot.entity.MovieImage;

public interface MovieService {

	Long register(MovieDTO movieDTO);
	
	//dto -> entity 변환 기본 메소드 정의
	//여기선 영화정보가 저장될 때 이미지도 저장되므로 movieImageDto -> entity 로 변환하는 로직도 추가해야 함.
	
	default Map<String, Object> dtoToEntity(MovieDTO movieDTO){
		Map<String, Object> entityMap = new HashMap<String, Object>();
		
		Movie movie = Movie.builder()
				.mno(movieDTO.getMno())
				.title(movieDTO.getTitle())
				.build();
		
		entityMap.put("movie", movie);
		
		List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();
		
		System.out.println(imageDTOList);
		
		if(imageDTOList != null && imageDTOList.size()>0) {
			List<MovieImage> movieImageList = imageDTOList.stream().map(movieImageDTO->{
				MovieImage movieImage = MovieImage.builder()
						.path(movieImageDTO.getFolderPath())
						.imgName(movieImageDTO.getFileName())
						.uuid(movieImageDTO.getUuid())
						.movie(movie)
						.build();
				return movieImage;
			}).collect(Collectors.toList());
			entityMap.put("imgList", movieImageList);
		}
		
		return entityMap;
	}
}
