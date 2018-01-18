package com.iu.qna;

import java.util.List;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.iu.board.BoardDAO;
import com.iu.board.BoardDTO;
import com.iu.util.ListData;

@Repository
public class QnaDAO implements BoardDAO { //Annotation 방식

	@Inject
	private SqlSession sqlSession;
	private final String NAMESPACE = "QnaMapper.";
	
	@Override
	public List<BoardDTO> selectList(ListData listData) throws Exception {
		return sqlSession.selectList(NAMESPACE+"selectList", listData);
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"selectOne", num);
	}

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"insert", boardDTO);
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"update", boardDTO);
	}

	@Override
	public int delete(int num) throws Exception {
		return sqlSession.delete(NAMESPACE+"delete", num);
	}
	
	@Override
	public int totalCount(ListData listData) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"totalCount", listData);
	}
	
	public int replyUpdate(BoardDTO boardDTO)throws Exception{
		return sqlSession.update(NAMESPACE+"replyUpdate", boardDTO);
	}
	
	public int replyInsert(BoardDTO boardDTO)throws Exception{
		return sqlSession.insert(NAMESPACE+"replyInsert", boardDTO);
	}

}
