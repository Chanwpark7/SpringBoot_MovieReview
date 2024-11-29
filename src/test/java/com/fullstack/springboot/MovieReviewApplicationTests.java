package com.fullstack.springboot;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.fullstack.springboot.dto.MovieDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResultDTO;
import com.fullstack.springboot.entity.Member;
import com.fullstack.springboot.entity.Movie;
import com.fullstack.springboot.entity.MovieImage;
import com.fullstack.springboot.entity.MovieReview;
import com.fullstack.springboot.repository.MemberRepository;
import com.fullstack.springboot.repository.MovieImageRepository;
import com.fullstack.springboot.repository.MovieRepository;
import com.fullstack.springboot.repository.MovieReviewRepository;
import com.fullstack.springboot.service.MovieService;

@SpringBootTest
class MovieReviewApplicationTests {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private MovieImageRepository movieImageRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MovieReviewRepository movieReviewRepository;
	
	@Autowired
	private MovieService movieService;
	
	@Test
	//Movie & MovieImage 데이터 밀어넣기 이미지수는 랜덤하게 생성
	//이미지의 다른 이름은 UUID 를 이용해서 랜덤한 문자열을 생성해서 사용.
//	void insertMovieAndImages() {
//		IntStream.rangeClosed(1, 100).forEach(value -> {
//			Movie movie = Movie.builder()
//					.title("movie "+value)
//					.build();
//			
//			movieRepository.save(movie);
//			
//			//MovieImage 는 Movie 객체를 참조하므로 여기서 같이 넣어줌
//			
//			int cnt = (int)(Math.random() * 5)+1;//1 ~ 5 개의 랜덤 이미지 생성
//			
//			for(int j=0; j<cnt;j++) {
//				MovieImage movieImage = MovieImage.builder()
//						.uuid(UUID.randomUUID().toString())
//						.movie(movie)
//						.imgName("test"+j+".jpg").build();
//				
//				movieImageRepository.save(movieImage);
//			}
//		});
//	}
	
	//Member dummy input
//	void insertMember() {
//		IntStream.rangeClosed(1, 100).forEach(i -> {
//			Member member = Member.builder()
//					.email("reply"+i+"@fullstack.com")
//					.pw("1111")
//					.nickname("리뷰어 "+i)
//					.build();
//					
//			memberRepository.save(member);
//		});
//	}

//	void insertReview() {
//		IntStream.rangeClosed(1, 100).forEach(i -> {
//			Long mno = (long)((Math.random()*100)+1);
//			//리뷰어 번호
//			Long mid = (long)((Math.random()*100)+101);
//			
//			Member member = Member.builder()
//					.mid(mid)
//					.build();
//			
//			MovieReview movieReview = MovieReview.builder()
//					.member(member)
//					.movie(Movie.builder().mno(mno).build())
//					.grade((int)(Math.random()*5)+1)
//					.text("Review for movie "+i).build();
//			
//			movieReviewRepository.save(movieReview);
//		});
//	}
	
	//영화에 따른 리뷰정보 쿼리 테스트
//	void testListPage() {
//		PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC,"mno"));
//		
//		Page<Object[]> result = movieRepository.getListPageWithImgs(pageRequest);
//		for(Object[] res : result.getContent()) {
//			System.out.println(Arrays.toString(res));
//		}
//	}
	
//	@Transactional
//	void testListPage() {
//		
//		List<Object[]> result = movieRepository.getReadPageWithImgs(1L);
//		for(Object[] res : result) {
//			System.out.println(Arrays.toString(res));
//		}
//	}
	
//	@Transactional
//	void testReadPage() {
//		Object[] result = movieReviewRepository.getReadByMno(1L);
//		for(Object res : result) {
//			System.out.println(res);
//		}
//	}
	
//	@Transactional
//	void testGetMovieWReview() {
//		Movie movie = Movie.builder().mno(1L).build();
//		
//		List<MovieReview> result = movieReviewRepository.findByMovie(movie);
//		System.out.println("result!!!!");
//		result.forEach(t -> {
//			System.out.println(t.getReviewnum());
//			System.out.println(t.getGrade());
//		});
//	}
	
	void testList() {
		PageRequestDTO pageRequestDto = PageRequestDTO
				.builder()
				.page(1)
				.size(10)
				.build();
		
		Pageable pageable = pageRequestDto.getPageable(Sort.by("mno").descending());
		
		Page<Object[]> result = movieRepository.getListPageWithImgs(pageable);
		result.forEach(t -> System.out.println(Arrays.toString(t)));
		
	}
}
