package com.fullstack.springboot.dto;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadResultDTO implements Serializable{

	private String fileName;
	private String uuid;
	private String folderPath;
	
	//image 경로 리턴 메소드 정의
	public String getImageUrl() {
		
		try {
			return URLEncoder.encode(folderPath + "/" + uuid + "_" + fileName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public String getThumbImageUrl() {
		
		try {
			return URLEncoder.encode(folderPath + "/s_" + uuid + "_" + fileName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
