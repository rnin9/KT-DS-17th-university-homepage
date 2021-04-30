package com.mySpring.springEx.courseTake.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mySpring.springEx.courseTake.vo.CourseTakeVO;

public interface CourseTakeDAO {
	public List selectAllApplyList() throws DataAccessException;

	// ��û����
	public int updateApplyConsent(CourseTakeVO courseTakeVO) throws Exception;

	// ����->���δ��� �ٽ� update
	public int updateApplyConsentCancel(CourseTakeVO courseTakeVO) throws Exception;

	// ������->����
	public int updateCompletion(CourseTakeVO courseTakeVO) throws Exception;

	// �����ϱ�
	public int deleteCourseTake(CourseTakeVO courseTakeVO) throws Exception;

	public int insertCourseTake(CourseTakeVO courseTakeVO) throws DataAccessException;

	public int updatePosition1(CourseTakeVO courseTakeVO) throws DataAccessException;

	public int updatePosition2(CourseTakeVO courseTakeVO) throws DataAccessException;
}