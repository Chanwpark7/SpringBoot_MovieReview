package com.fullstack.springboot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fullstack.springboot.dto.MovieDTO;
import com.fullstack.springboot.dto.MovieImageDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResultDTO;
import com.fullstack.springboot.entity.Movie;
import com.fullstack.springboot.entity.MovieImage;

public interface MovieService {
	
	//조회 페이지 처리와 리뷰 처리
	MovieDTO getMovie(Long mno);

	Long register(MovieDTO movieDTO);
	
	PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
	
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
	
	//entity -> dto 변환 메소드 정의
	default MovieDTO entityToDTO(Movie movie, List<MovieImage> images, Double avg, Long reviewCnt) {
		
		MovieDTO movieDTO = MovieDTO.builder()
				.mno(movie.getMno())
				.title(movie.getTitle())
				.moddate(movie.getModDate())
				.regdate(movie.getRegDate())
				.build();
		images.forEach(t -> System.out.println(t));
		List<MovieImageDTO> movieImages = images.stream().map(movieImage -> {
	         return MovieImageDTO.builder().fileName(movieImage.getImgName())
	               .folderPath(movieImage.getPath())
	               .uuid(movieImage.getUuid())
	               .build();
	      }).collect(Collectors.toList());
		
		movieDTO.setImageDTOList(movieImages);
		movieDTO.setAvg(avg);
		movieDTO.setReviewCnt(reviewCnt.intValue());
		
		return movieDTO;
	}
}
