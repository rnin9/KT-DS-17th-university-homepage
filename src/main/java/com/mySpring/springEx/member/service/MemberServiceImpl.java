package com.mySpring.springEx.member.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mySpring.springEx.courseTake.vo.CourseTakeVO;
import com.mySpring.springEx.member.dao.MemberDAO;
import com.mySpring.springEx.member.vo.MemberVO;
import com.mySpring.springEx.partner.vo.PartnerVO;

@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED)
@PropertySource("classpath:mailkey.properties")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;


	@Value("${hostSMTPid}")
	public String hostSMTPId;
	// https://m.blog.naver.com/monsterkn/221333152250 ���̹� SMTP������ �ڽ��� ���̵� ��й�ȣ ����

	// mailkey.properties hostSMTPpwd���� �ҷ���
	@Value("${hostSMTPpwd}")
	public String hostSMTPPwd;

	// mailkey.properties portNum���� �ҷ���
	@Value("${portNum}")
	public int portNum;

	@Override
	public List listMembers() throws DataAccessException {
		List membersList = null;
		membersList = memberDAO.selectAllMemberList();
		return membersList;
	}
	
	@Override
	public List listPartners() throws DataAccessException {
		List partnersName = null;
		partnersName = memberDAO.listPartners();
		return partnersName;
	}

	@Override
	public List listRecruitments() throws DataAccessException {
		List recruitmentList = null;
		recruitmentList = memberDAO.selectAllRecruitList();
		return recruitmentList;
	}

	@Override
	public List listApplications(String id) throws DataAccessException {
		List<HashMap<String, String>> applicationList = memberDAO.selectAllApplicationList(id);
		return applicationList;
	}

	@Override
	public List listSuggestions(String id) throws DataAccessException {
		List<HashMap<String, String>> suggestionList = memberDAO.selectAllSuggestionList(id);
		return suggestionList;
	}

	@Override
	public int userApplyPartner(String partnerApplyUserID, String partnerApplyPartnerID) throws Exception {
		return memberDAO.userApplyPartner(partnerApplyUserID, partnerApplyPartnerID);
	}

	@Override
	public int deleteApplication(String partnerApplyUserID, String partnerApplyPartnerID) throws Exception {
		return memberDAO.deleteApplication(partnerApplyUserID, partnerApplyPartnerID);
	}

	@Override
	public int deleteSuggestion(String partnerID, String userID) throws Exception {
		return memberDAO.deleteSuggestion(partnerID, userID);
	}

	@Override
	public int acceptSuggestion(String partnerID, String userID) throws Exception {
		return memberDAO.acceptSuggestion(partnerID, userID);
	}

	@Override
	public int rejectSuggestion(String partnerID, String userID) throws Exception {
		return memberDAO.rejectSuggestion(partnerID, userID);
	}


	/*
	 * @Override public int addMember(MemberVO member) throws DataAccessException {
	 * return memberDAO.insertMember(member); }
	 */


	@Override
	public int removeMember(String id) throws DataAccessException {
		return memberDAO.deleteMember(id);
	}

	@Override
	public MemberVO login(MemberVO memberVO) throws Exception {
		return memberDAO.loginById(memberVO);
	}

	// ������� ��������
	@Override
	public void check_id(String id, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println(memberDAO.check_id(id));
		out.close();
	}

	@Override
	public void check_email(String email, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println(memberDAO.check_email(email));
		System.out.println(out);
		out.close();
	}

	@Override
	public int join_member(MemberVO member, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("2");
		if (memberDAO.check_id(member.getUserId()) == 1) {		// �ߺ��� ���̵�
			out.println(
					"<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
			out.println("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
			out.println("<script language=JavaScript>");
			out.println("$(document).ready(function(){");
			out.println("swal('���̵� �ߺ�','�ߺ��� �ƴ� ���̵� �Է��ϼ���!','error')");
			out.println(".then((result) => {");
			out.println("history.go(-1);");
			out.println("})");
			out.println("});");
			out.println("</script>");
			out.close();
			return 0;
		} else if (memberDAO.check_email(member.getUserEmail()) == 1) { // �ߺ��� �̸���
			out.println(
					"<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
			out.println("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
			out.println("<script language=JavaScript>");
			out.println("$(document).ready(function(){");
			out.println("swal('�̸��� �ߺ�',' �ߺ��� �ƴ� �̸����� �Է��ϼ���!','error')");
			out.println(".then((result) => {");
			out.println("history.go(-1);");
			out.println("})");
			out.println("});");
			out.println("</script>");
			out.close();
			return 0;
		} else {

			// ����Ű set
			/* member.setApproval_key(create_key()); */
			memberDAO.join_member(member);
			// ���� ���� �߼�
			send_mail(member);
			out.println(
					"<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
			out.println("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
			
			out.println("<script src='https://cdn.jsdelivr.net/npm/sweetalert2@9'></script>");
			out.println("<script src='sweetalert2.min.js'></script>");
			
			out.println("<script language=JavaScript>");
			out.println("$(document).ready(function(){");
			
			out.println("Swal.fire({title:'ȸ�������� ���ϵ帳�ϴ�!!',	text: '������ ������ �Ϸ��ϼ���!', icon:'success', backdrop: 'rgba(0,0,123,0.4) url(\"/springEx/resources/image/nyan.gif\") left top no-repeat'})");
			//out.println("swal('������ ������ �Ϸ��ϼ���','������ �̸��Ϸ� ���۵Ǿ����ϴ�!','success')");
			
			out.println(".then((result) => {");
			out.println("location.href='http://localhost:"+portNum+"/springEx/main.do';");
			out.println("})");
			out.println("});");
			out.println("</script>");
			out.close();
			return 1;

		}
	}

	@Override
	public void approval_member(MemberVO member, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (memberDAO.approval_member(member) == 0) { // �̸��� ������ �����Ͽ��� ���
			out.println("<script>");
			out.println("alert('�߸��� �����Դϴ�.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		} else { // �̸��� ������ �����Ͽ��� ���
			out.println(
					"<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
			out.println("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
			out.println("<script language=JavaScript>");
			out.println("$(document).ready(function(){");
			out.println("swal('��������!!',' ������ �����ϼ̽��ϴ�','success')");
			out.println(".then((result) => {");
			out.println("history.go(-2);");
			out.println("})");
			out.println("});");
			
			out.println("</script>");
			out.close();
		}
	}

	

	@Override
	public void send_mail(MemberVO member) throws Exception {
		// Mail Server ����
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com";
		String hostSMTPid = hostSMTPId;
		String hostSMTPpwd = hostSMTPPwd;

		// ������ ��� EMail, ����, ����
		String fromEmail = hostSMTPId;
		String fromName = "KTDS-Spring Homepage �̸��� ����";
		String subject = "����";
		String msg = "������ ȯ���մϴ�.";

		// ȸ������ ���� ����
		subject = "KTDS Homepage ȸ������ ���� �����Դϴ�.";
		msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
		msg += "<h3 style='color: blue;'>";
		msg += member.getUserId() + "�� ȸ�������� ȯ���մϴ�.</h3>";
		msg += "<div style='font-size: 130%'>";
		msg += "�ϴ��� ���� ��ư Ŭ�� �� ���������� ȸ�������� �Ϸ�˴ϴ�.</div><br/>";
		msg += "<form method='post' action='http://localhost:" + portNum + "/springEx/member/approval_member.do'>"; // mailkey.properties
																													// ��
																													// portNum
		msg += "<input type='hidden' name='userEmail' value='" + member.getUserEmail() + "'>";
		msg += "<input type='submit' value='����'></form><br/></div>";
		System.out.println(msg);

	
		String mail = member.getUserEmail();
		System.out.println(mail);
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(465);

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("���Ϲ߼� ���� : " + e);
		}
	}

	@Override
	public MemberVO getMyInfo(String userID) throws Exception {
		// TODO Auto-generated method stub
		
		return memberDAO.getMyInformation(userID);
	}
	
	@Override
	public int modMyInfo(MemberVO member) throws Exception{
		memberDAO.modMyInfo(member);
		return 0;
	}
	
	@Override
	public List listMyCourse(String userID) throws DataAccessException {
		List myCourseList = null;
		myCourseList = memberDAO.selectAllMyCourseList(userID);
		return myCourseList;
	}

	@Override
	public PartnerVO partnerLogin(MemberVO memberVO) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.partnerLogInById(memberVO);
	}




	
	

}
