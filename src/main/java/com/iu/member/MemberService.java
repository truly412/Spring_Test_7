package com.iu.member;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	
	public int memberUpdate(MemberDTO memberDTO) throws Exception {
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

}
