package com.mySpring.springEx.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mySpring.springEx.common.pagination.Pagination;
import com.mySpring.springEx.notice.vo.NoticeVO;

public interface NoticeService {

	public List listNotice() throws Exception;

	public List listFixNotice() throws Exception;

	public void insertNotice(NoticeVO noticeVO, MultipartHttpServletRequest mpRequest) throws Exception;

	public NoticeVO readNotice(int notice_no) throws DataAccessException;

	// public void updateNotice(NoticeVO noticeVO) throws DataAccessException;
	public void deleteNotice(int notice_no) throws DataAccessException;

	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;

	public List<Map<String, String>> selectFileList(int notice_no) throws Exception;

	public void updateNotice(NoticeVO noticeVO, String[] files, String[] fileNames,
			MultipartHttpServletRequest mpRequest) throws Exception;
}
