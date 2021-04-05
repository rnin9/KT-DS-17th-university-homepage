package com.mySpring.springEx.survey.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mySpring.springEx.member.vo.MemberVO;
import com.mySpring.springEx.survey.vo.SurveyVO;

@Repository("surveyDAO")
public class SurveyDAOImpl implements SurveyDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List selectAllSurveyList() throws DataAccessException {
		List<SurveyVO> surveyList = null;
		surveyList = sqlSession.selectList("mapper.survey.selectAllSurveyList");
		return surveyList;
	}
	@Override
	public int insertSurvey(SurveyVO surveyVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.survey.insertSurvey", surveyVO);
		return result;
	}

}