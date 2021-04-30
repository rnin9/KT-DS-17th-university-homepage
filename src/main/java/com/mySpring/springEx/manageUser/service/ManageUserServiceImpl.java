package com.mySpring.springEx.manageUser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mySpring.springEx.member.dao.MemberDAO;

@Service("manageUserService")
@Transactional(propagation = Propagation.REQUIRED)
public class ManageUserServiceImpl implements ManageUserService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public List userList() throws DataAccessException{
		List userList = memberDAO.selectAllMemberList();
		return userList;
	}

}
