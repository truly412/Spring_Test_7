package com.iu.board;

import java.util.List;

import com.iu.util.ListData;

public interface BoardService {
	
	//SelectList
	public List<BoardDTO> selectList(ListData listData) throws Exception;
	
	//SelectOne
	public BoardDTO selectOne(int num) throws Exception;
	
	//Insert
	public int insert(BoardDTO boardDTO) throws Exception;
	
	//Update
	public int update(BoardDTO boardDTO) throws Exception;
	
	//Delete
	public int delete(int num) throws Exception;

}
