package com.fullstack.springboot.dto;


import java.time.LocalDateTime;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieReviewDTO {

	private Long reviewnum;
	
	private Long mno;
	
	private Long mid;
	
	private String nickname;
	
	private String email;
	
	//평점지수
	private int grade;
	
	private String text;
	
	private LocalDateTime regDate,modDate;
}
