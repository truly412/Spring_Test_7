package com.iu.member;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	
	@Inject
	private SqlSession sqlSession;
	private final String NAMESPACE = "MemberMapper.";

	public MemberDTO memberIdCheck(String id) {
		return sqlSession.selectOne(NAMESPACE+"idCheck", id);
	}

	public int memberJoin(MemberDTO memberDTO) {
		return sqlSession.insert(NAMESPACE+"memberJoin", memberDTO);
	}

	public MemberDTO memberLogin(MemberDTO memberDTO) {
		return sqlSession.selectOne(NAMESPACE+"memberLogin", memberDTO);
	}

	public MemberDTO memberView(String id) {
		return sqlSession.selectOne(NAMESPACE+"memberView", id);
	}
	
	public int memberUpdate(MemberDTO memberDTO) {
		return sqlSession.update(NAMESPACE+"memberUpdate", memberDTO);
	}
	
	public int memeberDelete(MemberDTO memberDTO) {
		return sqlSession.delete(NAMESPACE+"memeberDelete", memberDTO);
	}

}
