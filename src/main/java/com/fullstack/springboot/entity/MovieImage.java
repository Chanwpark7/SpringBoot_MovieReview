package com.fullstack.springboot.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "movie")
public class MovieImage extends BaseEntity {

	//이미지번호 PK
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inum;
	
	//웹에 게시될 때 변경되는 랜덤한 이름컬럼
	private String uuid;
	
	//origin name
	private String imgName;
	
	//이미지 경로
	private String path;
	
	//M : 1 의 참조관계 설정
	@ManyToOne(fetch = FetchType.LAZY)
	private Movie movie;
}
