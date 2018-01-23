package com.iu.notice;

import java.io.File;
import java.util.List;

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
	public int update(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int viewUpdate(BoardDTO boardDTO, MultipartFile[] file, HttpSession session) throws Exception {
		String filePath = session.getServletContext().getRealPath("resources/upload");
		File f = new File(filePath);
		if(!f.exists()){
			f.mkdirs();
		}
		FileSaver fs = new FileSaver();
		List<String> names = fs.saver(file, filePath);
		for(int i=0;i<names.size();i++) {
			FileDTO fileDTO = new FileDTO();
			fileDTO.setFname(names.get(i));
			fileDTO.setOname(file[i].getOriginalFilename());
			fileDTO.setNum(boardDTO.getNum());//위에 인서트 할때 boardDTO에 set해줬다
			fileDAO.insert(fileDTO);
		}
		int result = noticeDAO.update(boardDTO);
		return result;
	}
	
	@Override
	public int delete(int num,HttpSession session) throws Exception {
		String filePath = session.getServletContext().getRealPath("resources/upload");
		List<FileDTO> ar = fileDAO.selectList(num);
		int result = noticeDAO.delete(num);
		result = fileDAO.delete(num);
		for(FileDTO fileDTO : ar) {
			try{
			File file =  new File(filePath,fileDTO.getFname());
			file.delete();
			} catch (Exception e) {
				//왠지모르지만 디비에는있는데 실제파일이없는경우 익셉션걸리면서 나머지가 안지워지고종료되니
			}
		}
		
		return result;
	}
	
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
		
		List<String> names = fs.saver(file, filePath);
		noticeDAO.insert(boardDTO);
		for(int i=0;i<names.size();i++) {
			FileDTO fileDTO = new FileDTO();
			fileDTO.setFname(names.get(i));
			fileDTO.setOname(file[i].getOriginalFilename());
			fileDTO.setNum(boardDTO.getNum());//위에 인서트 할때 boardDTO에 set해줬다
			fileDAO.insert(fileDTO);
		}
		return 1;
	}
	
/*
	public void viewUpdate(int num, MultipartFile[] file, HttpSession session) throws Exception {
		String filePath = session.getServletContext().getRealPath("resources/upload");
		File f = new File(filePath);
		if(!f.exists()){
			f.mkdirs();
		}
		FileSaver fs = new FileSaver();
		fs.delete(num, session);
		List<String> names = fs.saver(file, filePath);
		FileDTO fileDTO = new FileDTO();
		fileDTO.setFname(fname);
		fileDTO.setOname(oname);
		
		
		fileDAO.viewUpdate(num);
		noticeDAO.viewUpdate(num);
	}
	*/
/*
	public void viewDelete(int num, HttpSession session) throws Exception {
		System.out.println("viewDelete in");
		NoticeDTO noticeDTO =  new NoticeDTO();
		noticeDTO = (NoticeDTO) noticeDAO.selectOne(num);
		for (FileDTO f : noticeDTO.getFiles()) {
			FileSaver fs = new FileSaver();
			System.out.println(f.getFname());
			
			fs.delete(f.getFname(),session);
		}
		
		
		fileDAO.viewdelete(num);
		noticeDAO.viewDelete(num);
		
	}
*/

	
}
