package com.iu.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.iu.util.ListData;

public interface BoardService {
	
	//SelectList
	public List<BoardDTO> selectList(ListData listData) throws Exception;
	
	//SelectOne
	public BoardDTO selectOne(int num) throws Exception;
	
	//Insert
	public int insert(BoardDTO boardDTO, MultipartFile[] file, HttpSession session) throws Exception;
	
	//Update
	public int update(BoardDTO boardDTO) throws Exception;
	
	//Delete
	public int delete(int num, HttpSession session) throws Exception;

}
