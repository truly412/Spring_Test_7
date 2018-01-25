package com.iu.member;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.file.FileDTO;
import com.iu.util.FileSaver;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;
	

	public MemberDTO memberLogin(MemberDTO memberDTO) throws Exception {
		return memberDAO.memberLogin(memberDTO);
	}
	
	/*public int memberDelete(String id,String pw) throws Exception {
		return memberDAO.memberDelete(id, pw);
	}*/
   public int memberUpdate(MemberDTO memberDTO, MultipartFile file, HttpSession session)throws Exception{
	      if(file != null){
	         String filePath = session.getServletContext().getRealPath("resources/upload");
	         File f = new File(filePath);
	         if(!f.exists()){
	            f.mkdirs();
	         }
	         FileSaver fs = new FileSaver();
	         String fileName = fs.saver(file, filePath);
	         memberDTO.setFname(fileName);
	         memberDTO.setOname(file.getOriginalFilename());
	      }else{
	         memberDTO.setFname(((MemberDTO)session.getAttribute("member")).getFname());
	         memberDTO.setOname(((MemberDTO)session.getAttribute("member")).getOname());
	      }
	      return memberDAO.memberUpdate(memberDTO);
	   }
	
	
	
	public MemberDTO memberView(String id) throws Exception {
		return memberDAO.memberView(id);
	}

	public MemberDTO memberIdCheck(String id) {
		return memberDAO.memberIdCheck(id);
	}

	public int memberJoin(MemberDTO memberDTO, MultipartFile file, HttpSession session) throws Exception {
		String filePath = session.getServletContext().getRealPath("resources/upload");
		System.out.println(filePath);
		File f = new File(filePath);
		if(!f.exists()){
			f.mkdirs();
		}
		FileSaver fs = new FileSaver();
		String name = fs.saver(file, filePath);
		memberDTO.setOname(file.getOriginalFilename());
		memberDTO.setFname(name);
		return memberDAO.memberJoin(memberDTO);
	}

	public int memberFileDelete(MemberDTO memberDTO, HttpSession session) {
		String filePath = session.getServletContext().getRealPath("resources/upload");
		File file = new File(filePath,memberDTO.getFname());
		int result;
		if(file.delete()) {
			result=1;
		} else { 
			result=0;
		}
		return result;
	}
	
	public int memberDelete(MemberDTO memberDTO) throws Exception {
		return 0;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
