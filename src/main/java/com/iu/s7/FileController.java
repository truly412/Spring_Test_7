package com.iu.s7;

import java.io.File;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iu.file.FileDAO;
import com.iu.file.FileDTO;
import com.iu.file.SeFileDTO;

@Controller
@RequestMapping(value="/file/**")
public class FileController {
	
	@Inject
	private FileDAO fileDAO;
	
	@RequestMapping(value="seUpload", method=RequestMethod.POST)
	   public String seUpload(SeFileDTO seFileDTO,HttpSession session) throws Exception {
	     String file_result="";
	     String callback = seFileDTO.getCallback();
	     String callback_func = seFileDTO.getCallback_func();
	     if(seFileDTO.getFiledata() != null && seFileDTO.getFiledata().getOriginalFilename() != null && !seFileDTO.getFiledata().getOriginalFilename().equals("")) {
	    	
	     //파일명
	     String fileName = seFileDTO.getFiledata().getOriginalFilename();
	     fileName = fileName.substring(fileName.lastIndexOf("."));
	     fileName = UUID.randomUUID().toString()+fileName;
	     
	     //저장경로
	     String filePath = session.getServletContext().getRealPath("resources/upload");
	     File f = new File(filePath);
	     if(!f.exists()){
	    	 f.mkdirs();
	     }
	     //저장
	     f=new File(filePath, fileName);
	     seFileDTO.getFiledata().transferTo(f);
	     file_result += "&bNewLine=true&sFileName="+seFileDTO.getFiledata().getOriginalFilename();
	     file_result += "&sFileURL=/s7/resources/upload/"+fileName;
	     } else {
	    	 file_result += "&errstr=error";
	     }
	     System.out.println("redirect:"+callback+"?callback_func="+callback_func+file_result);
	     return "redirect:"+callback+"?callback_func="+callback_func+file_result;
	   }
	
	
	
	@RequestMapping(value="fileDown")
	public ModelAndView fileDown(FileDTO fileDTO, HttpSession session) throws Exception {
		String filePath = session.getServletContext().getRealPath("resources/upload");
		File file = new File(filePath, fileDTO.getFname());
		ModelAndView mv = new ModelAndView();
		mv.addObject("down", file);
		mv.addObject("oname", fileDTO.getOname());
		mv.setViewName("fileDown");
		return mv;
	}
	
	@RequestMapping(value="fileDelete")
	public String fileDelete(FileDTO fileDTO, HttpSession session, Model model) throws Exception {
		String filePath = session.getServletContext().getRealPath("resources/upload");
		System.out.println(filePath);
		int result = fileDAO.deleteFnum(fileDTO.getFnum());
		if(result>0) {
			File file = new File(filePath,fileDTO.getFname());
			if(file.delete()){
				result=1;
			} else {
				result=0;
			}
		}
		model.addAttribute("result", result);
		return "common/fileResult";
		
	}

}
