package com.iu.file;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class FileDAO {
	
	@Inject
	private SqlSession sqlSession;
	private final String NAMESPACE="FileMapper.";
	
	public int insert(FileDTO fileDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"insert",fileDTO);
	}
	
	public List<FileDTO> selectList(int num)throws Exception{
		return sqlSession.selectList(NAMESPACE+"selectList", num);
	}

	public int viewUpdate(FileDTO fileDTO) {
		return sqlSession.update(NAMESPACE+"viewUpdate", fileDTO);
	}

	public int viewdelete(int num) {
		return sqlSession.delete(NAMESPACE+"viewDelete", num);
	}
	
	public int delete(int num) {
		return sqlSession.delete(NAMESPACE+"delete", num);
	}
	
	public int deleteFnum(int fnum) {
		return sqlSession.delete(NAMESPACE+"deleteFnum", fnum);
	}

}
