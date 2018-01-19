package com.iu.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileSaver {
	
	public String saver(MultipartFile file, String filePath) throws Exception {
		//1. 저장할 파일명 생성
		String fileName = file.getOriginalFilename();
		fileName = fileName.substring(fileName.lastIndexOf("."));
		fileName = UUID.randomUUID().toString()+fileName;
		//fileName = UUID.randomUUID().toString()+"_"+fileName;
		File f = new File(filePath,fileName);
		FileCopyUtils.copy(file.getBytes(), f);//저장할 소스 앞/ 뒤 파일객체
		return fileName;
	}
	
	public List<String> savaer(MultipartFile[] file, String filePath) throws Exception {
		List<String> fileNames = new ArrayList<String>();
		for(MultipartFile f : file) {
			String fileName = this.saver(f, filePath);
			fileNames.add(fileName);
		}
		return fileNames;
	}

}
