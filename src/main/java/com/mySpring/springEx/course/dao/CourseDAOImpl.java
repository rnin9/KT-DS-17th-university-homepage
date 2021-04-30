package com.mySpring.springEx.course.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.scheduling.annotation.Scheduled;

import com.mySpring.springEx.course.dao.CourseDAO;
import com.mySpring.springEx.course.vo.CourseVO;
import com.mySpring.springEx.syllabus.vo.SyllabusVO;

@Repository("courseDAO")
@Transactional(propagation=Propagation.REQUIRED)
public class CourseDAOImpl implements CourseDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List selectCourseList() throws DataAccessException{
		List<CourseVO> CourseList = null;
		CourseList = sqlSession.selectList("mapper.course.selectCourseList");
		return CourseList;
	}

	@Override
	public CourseVO selectCourse(int courseID) throws DataAccessException {
		CourseVO courseVO = sqlSession.selectOne("mapper.course.selectCourse",courseID);
		return courseVO;
	}

	@Override
	public int deleteCourse(int courseID) throws DataAccessException {
		int result = sqlSession.delete("mapper.course.deleteCourse", courseID);
		return result;
	}

	@Override
	public int insertCourse(CourseVO courseVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.course.insertCourse", courseVO);
		return result;
	}

	@Override
	public int modifyCourse(CourseVO courseVO) throws DataAccessException {
		int result = sqlSession.update("mapper.course.modifyCourse", courseVO);
		return result;
	}

	@Override
	public int closeCourse(int courseID) throws DataAccessException {
		int result = sqlSession.update("mapper.course.closeCourse", courseID);
		return result;
	}

	@Override
	public int openCourse(int courseID) throws DataAccessException {
		int result = sqlSession.update("mapper.course.openCourse", courseID);
		return result;
	}

	@Override
	public List selectCourseUserList() throws DataAccessException {
		List<CourseVO> CourseUserList = null;
		CourseUserList = sqlSession.selectList("mapper.course.selectUserCourseList");
		return CourseUserList;
	}

	@Override
	public CourseVO selectUserCourse(int courseID) throws DataAccessException {
		CourseVO courseVO = sqlSession.selectOne("mapper.course.selectUserCourse",courseID);
		return courseVO;
	}

	@Override
	public int updateCourse(int courseID) throws DataAccessException {
		return sqlSession.update("mapper.course.updateCourse", courseID);
	}

	@Scheduled(cron="0 0 0 * * *")
	public void autoUpdate() {
		System.out.println("123123");
		sqlSession.update("mapper.course.autoUpdateCourse");
	}
	
}