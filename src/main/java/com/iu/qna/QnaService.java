package com.iu.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.board.BoardDTO;
import com.iu.board.BoardService;
import com.iu.util.ListData;
import com.iu.util.PageMaker;

@Service
public class QnaService implements BoardService {
	
	@Autowired
	private QnaDAO qnaDAO;

	@Override
	public List<BoardDTO> selectList(ListData listData) throws Exception {
		int totalCount = qnaDAO.totalCount(listData);
		PageMaker pageMaker = new PageMaker();
		pageMaker.pageMaker(totalCount, listData);
		return qnaDAO.selectList(listData);
	}


	@Override
	public BoardDTO selectOne(int num) throws Exception {
		return qnaDAO.selectOne(num);
	}

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		return qnaDAO.insert(boardDTO);
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return qnaDAO.update(boardDTO);
	}

	@Override
	public int delete(int num) throws Exception {
		return qnaDAO.delete(num);
	}
	
	public int replyInsert(BoardDTO boardDTO) throws Exception{
		//한개의 Service 에서 2개이상의 DAO메서드를 가지고 올 수 있기때문에 굳이 replyUpdate메서드를 따로 생성하지 않아도 된다
		//또한 replyInsert메서드를 실행시 replyUpdate도 같이 실행 시켜야하기 때문에 더더욱 따로 생성할 필요가 없다
		BoardDTO parents = qnaDAO.selectOne(boardDTO.getNum());
		qnaDAO.replyUpdate(parents);
		QnaDTO parent = (QnaDTO)parents;
		QnaDTO qnaDTO = (QnaDTO)boardDTO;
		qnaDTO.setRef(parent.getRef());
		qnaDTO.setStep(parent.getStep()+1);
		qnaDTO.setDepth(parent.getDepth()+1);
		
		return qnaDAO.replyInsert(qnaDTO);
	}

}
