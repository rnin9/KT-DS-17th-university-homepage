package com.mySpring.springEx.courseTake.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mySpring.springEx.common.pagination.Pagination;
import com.mySpring.springEx.courseTake.dao.CourseTakeDAO;
import com.mySpring.springEx.courseTake.vo.CourseTakeVO;
import com.mySpring.springEx.syllabus.vo.SyllabusVO;
import com.mySpring.springEx.common.pagination.Pagination;
import com.mySpring.springEx.common.pagination.mapper.PageMapper;

@Service("courseTakeService")
@Transactional(propagation = Propagation.REQUIRED)
public class CourseTakeServiceImpl implements CourseTakeService {
	@Autowired
	private CourseTakeDAO courseTakeDAO;

	@Autowired
	public PageMapper pageMapper;

	@Override
	public List SelectAllCourseApplyList() throws Exception {
		return courseTakeDAO.selectAllApplyList();
	}

	// ���δ��->�������� update
	public int updateApplyConsent(CourseTakeVO courseTakeVO) throws Exception {
		return courseTakeDAO.updateApplyConsent(courseTakeVO);
	}

	// ����->���δ��� update
	public int updateApplyConsentCancel(CourseTakeVO courseTakeVO) throws Exception {
		return courseTakeDAO.updateApplyConsentCancel(courseTakeVO);
	}

	// ������->����� update
	public int updateCompletion(CourseTakeVO courseTakeVO) throws Exception {
		return courseTakeDAO.updateCompletion(courseTakeVO);
	}
	
	
	@Override
	public int insertCourseTake(CourseTakeVO courseTakeVO) throws DataAccessException {
		return courseTakeDAO.insertCourseTake(courseTakeVO);
	}

	// ������->����� update
	public int deleteCourseTake(CourseTakeVO courseTakeVO) throws Exception {
		return courseTakeDAO.deleteCourseTake(courseTakeVO);
	}

	@Override
	public int updatePosition1(CourseTakeVO courseTakeVO) throws DataAccessException {
		return courseTakeDAO.updatePosition1(courseTakeVO);
	}
	
	@Override
	public int updatePosition2(CourseTakeVO courseTakeVO) throws DataAccessException {
		return courseTakeDAO.updatePosition2(courseTakeVO);
	}
}