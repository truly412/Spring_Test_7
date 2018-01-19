package com.iu.file;

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

}
