package com.fullstack.springboot.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MovieDTO {

	private Long mno;
	private String title;
	
	//영화에 대한 업로드된 이미지 필드. 하나 이상의 이미지 정보일 수 있으니 List<MovieImageDTO> 타입으로 선언
	@Builder.Default
	private List<MovieImageDTO> imageDTOList = new ArrayList();
	
	//영화에 대한 일반적인 정보를 가져다가 list 에 뿌려야 하기 때문에, 필드를 추가함.
	
	//영화의 평점
	private double avg;
	
	//리뷰 수
	private int reviewCnt;
	
	private LocalDateTime regdate;
	private LocalDateTime moddate;
}
