package com.fullstack.springboot.dto;

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
}
