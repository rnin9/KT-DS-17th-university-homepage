package com.mySpring.springEx.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mySpring.springEx.member.vo.MemberVO;


@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}

	@Override
	public int deleteMember(String id) throws DataAccessException {
		int result = sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}

	@Override
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException {
		MemberVO vo = sqlSession.selectOne("mapper.member.loginById", memberVO);
		return vo;
	}
	
	//(ȸ�������ϱ����� �߰��Ȱ� - munsu)
	// ���̵� �ߺ� �˻�
	@Override
	public int check_id(String id) throws Exception {
		return sqlSession.selectOne("mapper.member.check_id", id);
	}

	// �̸��� �ߺ� �˻�
	@Override
	public int check_email(String email) throws Exception {
		return sqlSession.selectOne("mapper.member.check_email", email);
	}

	// ȸ������
	@Transactional
	@Override
	public int join_member(MemberVO member) throws Exception {
		return sqlSession.insert("mapper.member.join_member", member);
	}

	// �̸��� ����
	@Transactional
	@Override
	public int approval_member(MemberVO member) throws Exception {
		return sqlSession.update("mapper.member.approval_member", member);
	}



}
