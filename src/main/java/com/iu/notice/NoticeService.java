package com.iu.notice;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.board.BoardDTO;
import com.iu.board.BoardService;
import com.iu.file.FileDAO;
import com.iu.file.FileDTO;
import com.iu.util.FileSaver;
import com.iu.util.ListData;
import com.iu.util.PageMaker;

@Service
public class NoticeService implements BoardService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	@Autowired
	private FileDAO fileDAO;

	@Override
	public List<BoardDTO> selectList(ListData listData) throws Exception {
		int totalCount = noticeDAO.totalCount(listData);
		PageMaker pageMaker = new PageMaker();
		pageMaker.pageMaker(totalCount, listData);
		return noticeDAO.selectList(listData);
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		return noticeDAO.selectOne(num);
	}
	
	@Override
	public int insert(BoardDTO boardDTO, MultipartFile[] file, HttpSession session) throws Exception {
		String filePath = session.getServletContext().getRealPath("resources/upload");
		System.out.println(filePath);
		File f = new File(filePath);
		if(!f.exists()){
			f.mkdirs();
		}
		FileSaver fs = new FileSaver();
		//FileDAO fileDAO = new FileDAO();//개발자가 직접 객체만들면 inject안시켜줌
		
		List<String> names = fs.savaer(file, filePath);
		int num = noticeDAO.num();	//board_seq.nextval을 가져옴
		boardDTO.setNum(num);
		noticeDAO.insert(boardDTO);
		for(int i=0;i<names.size();i++) {
			FileDTO fileDTO = new FileDTO();
			fileDTO.setFname(names.get(i));
			fileDTO.setOname(file[i].getOriginalFilename());
			fileDTO.setNum(num);
			fileDAO.insert(fileDTO);
		}
		return 1;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return noticeDAO.update(boardDTO);
	}

	@Override
	public int delete(int num) throws Exception {
		return noticeDAO.delete(num);
	}

}
