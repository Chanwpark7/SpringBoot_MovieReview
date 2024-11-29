package com.fullstack.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fullstack.springboot.dto.UploadResultDTO;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@Log4j2
public class UploadController {

	//필드를 선언해서 저장 경로를 @value 를 이용, application 으로부터 저장 경로 값을 get
	@Value("${com.fullstack.springboot.uploadPath}")
	private String uploadPath;
	
	//업로드된 이미지를 보여주는 URL 매핑작성
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(@RequestParam("fileName") String fileName) {
		ResponseEntity<byte[]> result = null;
		
		try {
			String srcFileName = URLDecoder.decode(fileName,"UTF-8");
			
			log.error("fileName = "+ srcFileName);
			
			//보여줄 Imaga File 객체 생성 및 Header 를 통한 이미지 스트림 전송
			File file = new File(uploadPath+File.separator+srcFileName);
			
			log.error("다운될 파일 : "+file);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", Files.probeContentType(file.toPath()));//마임타입 헤더 설정
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),headers,HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return result;
	}
	
	@PostMapping("/uploadAjax")
	public ResponseEntity<List<UploadResultDTO>> postMethodName(@RequestParam(value = "uploadFiles") MultipartFile[] multipartFiles) {//파일 업로드 시, 파일들은 자동으로 메소드의 파라미터 배열에 담김
		//넘겨줄 이미지 List 생성
		List<UploadResultDTO> list = new ArrayList<UploadResultDTO>();
		
		for(MultipartFile multipartFile : multipartFiles) {
			
			//넘겨줄 파일의 정보 추출
			if(multipartFile.getContentType().startsWith("image")==false) {
				log.error("이미지 파일 아닌데?");
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);//403 에러 유발. 화면에는 403 에러가 뜸.
			}
			
			String orgName = multipartFile.getOriginalFilename();
			String fileName = orgName.substring(orgName.lastIndexOf("\\")+1);
			
			log.error("fileName: " + fileName);
			
			//실제 저장될 root 하위의 날짜별 폴더 경로 get
			String folderPath = makeFolder();
			
			//UUID 를 이용해서 업로드될 파일의 이름 변경.
			String uuid = UUID.randomUUID().toString();
			
			String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
			
			log.error("저장될 파일 이름 : " + saveName);
			
			//nio 패키지의 Path 객체를 이용해서 스트림 전송
			Path path = Paths.get(saveName);
			
			try {
				multipartFile.transferTo(path);
				
				//썸네일 생성
				String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + "_" + fileName;
				
				File thumbFile = new File(thumbnailSaveName);
				
				Thumbnailator.createThumbnail(path.toFile(), thumbFile, 100, 100);
				
				//파일이 저장된 이후에 바로 list 에 담아서 되돌린다.
				list.add(new UploadResultDTO(fileName,uuid,folderPath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ResponseEntity.ok(list);
	}
	
	//업로드되는 파일을 일자별로 폴더로 관리함.
	//아래 메소드는 그 내용을 정의함.
	private String makeFolder() {
		String folderPath = "";
		
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		
		folderPath = str.replace("/", File.separator);
		
		File uploadPathFolder = new File(uploadPath, folderPath);//실제 저장 경로
		
		if(!uploadPathFolder.exists()) {
			uploadPathFolder.mkdirs();
		}
		
		return folderPath;
	}
}
