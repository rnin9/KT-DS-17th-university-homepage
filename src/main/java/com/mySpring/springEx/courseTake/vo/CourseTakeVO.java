package com.mySpring.springEx.courseTake.vo;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mySpring.springEx.member.vo.MemberVO;

@Component("courseTakeVO")
public class CourseTakeVO {

	

	private String userID;
	private int courseID;
	private String courseTake_State;
	private Date courseTake_ApplyDate;
	private Date courseTake_CompleteDate;
	/*
	 * private String userName; private String userPhoneNumber; private String
	 * userEmail; private String userCompany;
	 */

	// kt_user�� join�ϱ� ���� memberVO ��ü�� ��������� �����Ѵ�.
	@Autowired
	private MemberVO memberVO;
/*
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	*/
	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public CourseTakeVO() {

	}

	public CourseTakeVO(String userID, int courseID, String courseTake_State, Date courseTake_ApplyDate, Date courseTake_CompleteDate, MemberVO memberVO) {

		this.userID = userID;
		this.courseID = courseID;
		this.courseTake_State = courseTake_State;
		this.courseTake_ApplyDate = courseTake_ApplyDate;
		this.courseTake_CompleteDate = courseTake_CompleteDate;
		this.memberVO = memberVO;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getCourseTake_State() {
		return courseTake_State;
	}

	public void setCourseTake_State(String courseTake_State) {
		this.courseTake_State = courseTake_State;
	}

	public Date getCourseTake_ApplyDate() {
		return courseTake_ApplyDate;
	}

	public void setCourseTake_ApplyDate(Date courseTake_ApplyDate) {
		this.courseTake_ApplyDate = courseTake_ApplyDate;
	}

	public Date getCourseTake_CompleteDate() {
		return courseTake_CompleteDate;
	}

	public void setCourseTake_CompleteDate(Date courseTake_CompleteDate) {
		this.courseTake_CompleteDate = courseTake_CompleteDate;
	}

	

}