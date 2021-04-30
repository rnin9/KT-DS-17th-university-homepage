package com.mySpring.springEx.member.dao;

import java.util.HashMap;
import java.util.List;

import com.mySpring.springEx.application.vo.ApplicationVO;
import com.mySpring.springEx.courseTake.vo.CourseTakeVO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mySpring.springEx.member.vo.MemberVO;
import com.mySpring.springEx.partner.vo.PartnerVO;
import com.mySpring.springEx.survey.vo.SurveyVO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private PartnerVO partnerVO;

	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}

	@Override
	public List listPartners() throws DataAccessException {
		List<SurveyVO> partnersName = null;
		partnersName = sqlSession.selectList("mapper.member.selectAllPartnerList");
		return partnersName;
	}

	@Override
	public List selectAllRecruitList() throws DataAccessException {
		List<PartnerVO> partnerList = null;
		partnerList = sqlSession.selectList("mapper.member.selectAllRecruitList");
		return partnerList;
	}

	@Override
	public List selectAllApplicationList(String id) throws DataAccessException {
		List<HashMap<String, String>> applicationList = sqlSession.selectList("mapper.member.selectAllApplicationList",
				id);
		return applicationList;
	}

	@Override
	public List selectAllSuggestionList(String id) throws DataAccessException {
		List<HashMap<String, String>> suggestionList = sqlSession.selectList("mapper.member.selectAllSuggestionList", id);
		return suggestionList;
	}

	public int userApplyPartner(String partnerApplyUserID, String partnerApplyPartnerID) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("partnerApplyUserID", partnerApplyUserID);
		map.put("partnerApplyPartnerID", partnerApplyPartnerID);
		return sqlSession.insert("mapper.member.userApplyPartner", map);
	}

	public int deleteApplication(String partnerApplyUserID, String partnerApplyPartnerID) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("partnerApplyUserID", partnerApplyUserID);
		map.put("partnerApplyPartnerID", partnerApplyPartnerID);
		return sqlSession.delete("mapper.member.deleteApplication", map);
	}

	public int deleteSuggestion(String partnerID, String userID) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("partnerID", partnerID);
		map.put("userID", userID);
		return sqlSession.update("mapper.member.deleteSuggestion", map);
	}

	public int acceptSuggestion(String partnerID, String userID) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("partnerID", partnerID);
		map.put("userID", userID);
		return sqlSession.update("mapper.member.acceptSuggestion", map);
	}

	public int rejectSuggestion(String partnerID, String userID) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("partnerID", partnerID);
		map.put("userID", userID);
		return sqlSession.update("mapper.member.rejectSuggestion", map);
	}

	@Override
	public int deleteMember(String id) throws DataAccessException {
		int result = sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}

	@Override
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException {
		MemberVO vo = sqlSession.selectOne("mapper.member.loginById", memberVO);
		if (vo != null) {
			vo.setResume((String) sqlSession.selectOne("mapper.member.check_resume", vo.getUserId()));
		}
		return vo;
	}

	// test��
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

	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	// ������������ ������
	@Override
	public MemberVO getMyInformation(String userID) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.member.get_myInformation", userID);
	}

	@Override
	public int modMyInfo(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("mapper.member.mod_myInformation", member);
	}
	
	//���� ������û����
	@Override
	public List selectAllMyCourseList(String userID) throws DataAccessException {
		List myCourseList = null;
		myCourseList = sqlSession.selectList("mapper.courseTake.get_myCourseInformation", userID);
		return myCourseList;
	}

	@Override
	public PartnerVO partnerLogInById(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.member.partnerLoginById", member);
	}

}
