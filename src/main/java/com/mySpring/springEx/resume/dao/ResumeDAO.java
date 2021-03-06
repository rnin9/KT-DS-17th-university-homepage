package com.mySpring.springEx.resume.dao;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;

import com.mySpring.springEx.resume.vo.ResumeVO;

public interface ResumeDAO {
	public List<ResumeVO> selectResumeList(String resumeUser) throws DataAccessException;
	
	public void resumeCheck(ResumeVO resume) throws DataAccessException;
	
	public List selectAllResumeList() throws DataAccessException;
	
	public ResumeVO resumeInfo(String resumeID) throws DataAccessException;
	
	public List<ResumeVO> resumeInfo_career(String resumeUser) throws DataAccessException;
	
	public List<ResumeVO> resumeInfo_certificate(String resumeUser) throws DataAccessException;
	
	public List<ResumeVO> resumeInfo_foreign(String resumeUser) throws DataAccessException;
	
	public List<ResumeVO> resumeInfo_project(String resumeUser) throws DataAccessException;
	
	public void addResume(ResumeVO resume) throws DataAccessException;
	
	public int deleteResume (String resumeID) throws DataAccessException;
	
	public String getResumeUser(String resumeID) throws DataAccessException;
	
	public void resumePic(ResumeVO resume) throws DataAccessException;
	
	//public int insertResume(ResumeVO resume) throws DataAccessException;
	
	//page2
	
	public List<ResumeVO> selectCertificafeList(ResumeVO resume) throws DataAccessException;
	
	public List<ResumeVO> selectForeignList(ResumeVO resume) throws DataAccessException;
	
	public void insertCertificate(ResumeVO resume) throws DataAccessException;
	
	public void deleteCertificate(int certificateSEQ) throws DataAccessException;
	
	public void insertForeign(ResumeVO resume) throws DataAccessException;
	
	public void deleteForeign(int foreignSEQ) throws DataAccessException;
	
	public void updatePage2Education(ResumeVO resume) throws DataAccessException;
	
	public void updatePage2Certificate(ResumeVO resume) throws DataAccessException;
	
	public void updatePage2Foreign(ResumeVO resume) throws DataAccessException;
	
	//page3
	
	public void insertCareer(ResumeVO resume) throws DataAccessException;
	
	public void insertEducation(ResumeVO resume) throws DataAccessException;
	
	public void deleteCareer(int careerSEQ) throws DataAccessException;
	
	public List<ResumeVO> selectCareerList(ResumeVO resume) throws DataAccessException;
	
	public List<ResumeVO> selectEducationList(ResumeVO resume) throws DataAccessException;
	
	public void updatePage3Career(ResumeVO resume) throws DataAccessException;
	
	//page4
	
	public void insertProject(ResumeVO resume) throws DataAccessException;
	
	public void deleteProject(int projectSEQ) throws DataAccessException;
	
	public List<ResumeVO> selectProjectList(ResumeVO resume) throws DataAccessException;
	
	public void updatePage4Project(ResumeVO resume) throws DataAccessException;
	
	//page5
	
	public void updatePage5Self(ResumeVO resume) throws DataAccessException;
	
	public void createResume(ResumeVO resume) throws DataAccessException;

}
