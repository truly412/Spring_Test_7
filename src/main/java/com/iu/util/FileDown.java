package com.iu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDown extends AbstractView {
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FileDown");
		File file = (File) model.get("down");
		response.setCharacterEncoding("UTF-8");
		response.setContentLength((int)file.length());//contents의 길이 (파일의 크기)//롱타입임
		
		String fileName = URLEncoder.encode((String)model.get("oname"), "UTF-8");
		
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
		response.setHeader("Content-Transfer-Encoding", "binary");//전송할때 어떤형식으로 할거냐 ex)text로보내고싶으면 택스트로보냄 //파일은 비트코드로 전송
		
		FileInputStream fi = new FileInputStream(file);//읽오옴~
		OutputStream os = response.getOutputStream();//신청한놈으로부터 (해더를받아옴)
		
		FileCopyUtils.copy(fi, os);//인한거를 아웃으로 보내라~
		fi.close();
		os.close();
	}

}
