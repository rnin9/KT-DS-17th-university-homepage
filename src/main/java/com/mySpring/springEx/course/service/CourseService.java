package com.mySpring.springEx.course.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mySpring.springEx.course.vo.CourseVO;

public interface CourseService {
	public List courseList() throws DataAccessException;
	
	public CourseVO selectCourse(int courseID) throws DataAccessException;

	public int deleteCourse(int courseID) throws DataAccessException;
	
	public int insertCourse(CourseVO courseVO) throws DataAccessException;

	public int modifyCourse(CourseVO courseVO) throws DataAccessException;

	public int closeCourse(int courseID) throws DataAccessException;

	public int openCourse(int courseID) throws DataAccessException;

	public List courseUserList() throws DataAccessException;

	public CourseVO selectUserCourse(int courseID) throws DataAccessException;

	public int updateCourse(int courseID) throws DataAccessException;

}
