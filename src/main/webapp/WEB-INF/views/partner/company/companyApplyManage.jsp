<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
	
   
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<style>
.table thead th {
	width: 25%;
	vertical-align: bottom;
	border-bottom: 2px solid #dee2e6;
}
</style>
<script>
        $(document).ready(function () {
            let activeTab = sessionStorage.getItem('activeTab');
            // ���ΰ�ħ �� �� ���� ����
            $('#myTab a[href="'+activeTab+'"]').trigger('click');
        });

        // ��� body text ����
        
        function getResumeInfo(resumeID) {
        	
        	$.ajax({				// �񵿱����, �̷¼� ��������
	            method: "GET",
	            url: "${contextPath}/partner/getResumeByID.do?partnerApplyResumeID="+resumeID,
	            success: (resp) => {	// ��� ����� success�� ����
	            	$("#modal_title").text(resp.resume.resumeCheck);
                    $("#partner_info").text(resp.resume.resumeDate);
                    $("#partner_addr").text(resp.resume.resumeID);
                    $("#partner_email").text(resp.resume.resumeUser);
	            },
	            error: (err) => {
	                console.log(err+" �񵿱� ����");
	     	}
	        })
        	
            }

        // �� Ŭ�� �� session�� ���� �� �� ����
        function tabtab(h) {
            sessionStorage.setItem('activeTab', h);
            console.log('href   yyyy' + h);
        }


        // Check Pass Or Fail 
        function chk_passOrFail(userID, userName, partnerID) {
        	    Swal.fire({
        	        title:userName+'('+userID+')',
     				icon:'warning',
        	        confirmButtonText: `�հ�`,
        	        confirmButtonColor: '#3085d6',
        	        showCloseButton: true,
        	        showCancelButton: true,
        	        cancelButtonText: `���հ�`,
        	        cancelButtonColor: '#d33',
        	        }).then((res) => {
          			if(res.isConfirmed){
          				
          			Swal.fire({               /* update operation start */
          				     title:'�հ� ó��',
          				     text: '�հ� ó���Ͻðڽ��ϱ�?',
          				     showCancelButton: true,
          				     showCloseButton: true,
          				     icon:"success",
          				     confirmButtonColor: '#3085d6',
          				     cancelButtonColor: '#d33',
							 cancelButtonText: '���',
          				     confirmButtonText: '�հ�'
          				   }).then((result) => {
          				     /* Read more about isConfirmed, isDenied below */
          				     if (result.isConfirmed) {
          				    	 $.ajax({				//check update to pass
          		      	              method: "POST",
          		      	              url: "${contextPath}/partner/company/manageApply.do",
          		      	              data: {
          		      	            	partnerApplyUserID    : userID,
          		      	              	partnerApplyPartnerID : partnerID,
          		      	                partnerApplyState : '�հ�'
          		      	              },
          		      	              success: (resp) => {	// update to pass and reloading
          		      	            	location.reload();        
          		      	              },
          		      	              error: (data) => {
          		      	                  console.log("������ ���� ����"+data);
          		      	               }
          		      	          }) 
          				     } else{
          				         return;     /* cancel operation */
          				     }
          				   })
          			
      	          } else if( res.dismiss ==='cancel'){
      	        	Swal.fire({               /* check update to fail */
     				     title:'���հ� ó��',
     				     text: '���հ� ó���Ͻðڽ��ϱ�?',
     				     showCloseButton: true,
     				     showCancelButton: true,
     				     icon:"error",
     				     confirmButtonColor: '#d33',
     				     cancelButtonColor: '#3085d6#d33',
     				    cancelButtonText: '���',
     				     confirmButtonText: '���հ�'
     				   }).then((result) => {
     				     /* Read more about isConfirmed, isDenied below */
     				     if (result.isConfirmed) {
     				    	 $.ajax({				// update to fail
     		      	              method: "POST",
     		      	              url: "${contextPath}/partner/company/manageApply.do",
     		      	              data: {
     		      	            	partnerApplyUserID    : userID,
      		      	              	partnerApplyPartnerID : partnerID,
      		      	                partnerApplyState : '���հ�'
      		      	              
     		      	              },
     		      	              success: (resp) => {	// update to fail and reloading
     		      	            	location.reload();        
     		      	              },
     		      	              error: (data) => {
     		      	                  console.log("������ ���� ����"+data);
     		      	               }
     		      	          }) 
     				     } else{
     				         return;     /* cancel update */
     				     }
     				   })
      	        } else{
      	        	 return;
      	          }
          			  
        	      })
        	}
        
        function chk_reset(userID, userName, partnerID) {
    	    Swal.fire({
    	        title:'���� ó��',
    	        text:'�ش� ����� �����մϴ�',
 				icon:'warning',
    	        confirmButtonText: `�հ�`,
    	        confirmButtonColor: '#3085d6',
    	        showCloseButton: true,
    	        showCancelButton: true,
    	        cancelButtonText: `���հ�`,
    	        cancelButtonColor: '#d33',
    	        }).then((res) => {
      			if(res.isConfirmed){
      				
      			Swal.fire({               /* check editing */
      				     title:'�հ� ó��',
      				     text: '�հ� ó���Ͻðڽ��ϱ�?',
      				     showCancelButton: true,
      				     showCloseButton: true,
      				     icon:"success",
      				     confirmButtonColor: '#3085d6',
      				     cancelButtonColor: '#d33',
						 cancelButtonText: '���',
      				     confirmButtonText: '�հ�'
      				   }).then((result) => {
      				     /* Read more about isConfirmed, isDenied below */
      				     if (result.isConfirmed) {
      				    	 $.ajax({				// update to pass 
      		      	              method: "POST",
      		      	              url: "${contextPath}/partner/company/manageApply.do",
      		      	              data: {
      		      	            	partnerApplyUserID    : userID,
      		      	              	partnerApplyPartnerID : partnerID,
      		      	                partnerApplyState : '�հ�'
      		      	              },
      		      	              success: (resp) => {	// update success and reloading
      		      	            	location.reload();        
      		      	              },
      		      	              error: (data) => {
      		      	                  console.log("������ ���� ����"+data);
      		      	               }
      		      	          }) 
      				     } else{
      				         return;     /* cancel editing */
      				     }
      				   })
      			
  	          } else if( res.dismiss ==='cancel'){
  	        	Swal.fire({               /* edit to fail */
 				     title:'���հ� ó��',
 				     text: '���հ� ó���Ͻðڽ��ϱ�?',
 				     showCloseButton: true,
 				     showCancelButton: true,
 				     icon:"error",
 				     confirmButtonColor: '#d33',
 				     cancelButtonColor: '#3085d6',
 				    cancelButtonText: '���',
 				     confirmButtonText: '���հ�'
 				   }).then((result) => {
 				     /* Read more about isConfirmed, isDenied below */
 				     if (result.isConfirmed) {
 				    	 $.ajax({				// update to not allowed
 		      	              method: "POST",
 		      	              url: "${contextPath}/partner/company/manageApply.do",
 		      	              data: {
 		      	            	partnerApplyUserID    : userID,
  		      	              	partnerApplyPartnerID : partnerID,
  		      	                partnerApplyState : '���հ�'
  		      	              
 		      	              },
 		      	              success: (resp) => {	// update success and reloading
 		      	            	location.reload();        
 		      	              },
 		      	              error: (data) => {
 		      	                  console.log("������ ���� ����"+data);
 		      	               }
 		      	          }) 
 				     } else{
 				         return;     /* �������� ��� */
 				     }
 				   })
  	        } else{
  	        	 return;
  	          }
      			  
    	      })
    	}
        
        function suggestToUser(userID, partnerID, userName) {
        	Swal.fire({
    	    	html: `
    	    	<h2>ä�� ����</h2>
    	    	<input type="text" id="user" style="background-color: #eee;" class="swal2-input" readonly>
    	    	<input type="text" id="suggestTitle" placeholder="����" class="swal2-input">
    	    	<textarea type="text" id="suggestDesc" placeholder="���� ����" style="height:300px; padding-top: 5px;" class="swal2-input" rows="100"></textarea>`,
    	        confirmButtonText: `����`,
    	        confirmButtonColor: '#3085d6',
    	        showCloseButton: true,
    	        showCancelButton: true,
    	        cancelButtonText: `���`,
    	        cancelButtonColor: '#d33',
    	        onOpen: function() {
                    $('#user').attr("value", userName);
                },
                preConfirm: () => {			// pre confirm
                	const suggestDesc = Swal.getPopup().querySelector('#suggestDesc').value;
          			const suggestTitle = Swal.getPopup().querySelector('#suggestTitle').value;
          			if (!suggestDesc || !suggestTitle) {
        		      Swal.showValidationMessage(`Please enter title and Description`)	// check title and Descrtion is not null
        		    }
        		    else{
        		    	return;
        		}
          		}
    	        }).then((res) => {
      			if(res.isConfirmed){
      			const suggestDesc = Swal.getPopup().querySelector('#suggestDesc').value;
          		const suggestTitle = Swal.getPopup().querySelector('#suggestTitle').value;
          		Swal.fire({               /* check editing */
      				     title:'������ ����',
      				     text: 'ä�� �����Ͻðڽ��ϱ�?',
      				     showCancelButton: true,
      				     showCloseButton: true,
      				     icon:"success",
      				     confirmButtonColor: '#3085d6',
      				     cancelButtonColor: '#d33',
						 cancelButtonText: '���� ���',
      				     confirmButtonText: 'ä�� ����'
      				   }).then((result) => {
      				     /* Read more about isConfirmed, isDenied below */
      				     if (result.isConfirmed) {
      				    	
      				    	 $.ajax({				// update to pass 
      		      	              method: "POST",
      		      	              url: "${contextPath}/partner/company/manageSuggest.do",
      		      	              data: {
      		      	            	partnerSuggestionUserID    : userID,
      		      	              	partnerSuggestionPartnerID : partnerID,
      		      	              	partnerSuggestionTitle : suggestTitle,
      		      	                partnerSuggestionDescription : suggestDesc
      		      	              },
      		      	              success: (resp) => {	// update success and reloading
      		      	            	 Swal.fire('���ȿϷ�!', '', 'success').then(()=>{
      		      	            	 location.reload(); 
      		      	             	})
      		      	              },
      		      	              error: (data) => {
      		      	                  console.log("������ ���� ����"+data);
      		      	               }
      		      	          })  
      				     } else{
      				         return;     /* cancel editing */
      				     }
      				   })
      			
  	          } else{
  	        	 return;
  	          } 
    	      })
    	}
        
        function deleteSuggestion(userID, partnerID){
        	 Swal.fire({
     	        title:'ä������ ����',
     	        text:'���� �Ŀ� ������ �� �����ϴ�.',
  				icon:'warning',
     	        confirmButtonText: `����`,
     	        confirmButtonColor: '#d33',
     	        showCloseButton: true,
     	        showCancelButton: true,
     	        cancelButtonText: `���`,
     	        cancelButtonColor: '#3085d6',
     	        }).then((res) => {
       			if(res.isConfirmed){
       				    	 $.ajax({				//check update to pass
       		      	              method: "POST",
       		      	              url: "${contextPath}/partner/company/deleteSuggest.do",
       		      	              data: {
       		      	            	partnerSuggestionUserID    : userID,
       		      	              	partnerSuggestionPartnerID : partnerID,
       		      	                partnerSuggestionPartnerD : 'N'
       		      	              },
       		      	              success: (resp) => {	// update to pass and reloading
       		      	            	location.reload();        
       		      	              },
       		      	              error: (data) => {
       		      	                  console.log("������ ���� ����"+data);
       		      	               }
       		      	          }) 
       			} 
   	          else{
   	        	 return;
   	          	 }
       			})
        }
     </script>
<body>
	<div id="applyContents">
		<div class="sub_visual">
			<span style="color: white;">���»� ����</span>
		</div>
		<div class="container"
			style="display: flex; flex-wrap: wrap; width: 75%; justify-content: space-around; flex-direction: column; padding-bottom: 200px;">

			<!-- Modal -->
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog modal-dialog-scrollable">

					<!-- Modal content-->
					<div class="modal-content">

						<div class="modal-header">
							<h5 class="modal-title" id="modal_title"></h5>
							<button type="button" class="close" data-dismiss="modal">��</button>
						</div>
						<div class="modal-body">
							<div class="partnerInfoModalBody" style="text-align: left">
								<div class="row">
									<div class="col-3" style="color: #444444; font-weight: bold">
										<p>�Ұ�</p>
										<p>�ּ�</p>
										<p>�����</p>
										<p>�̸���</p>
										<p>������Ʈ</p>
									</div>
									<div class="col-8">
										<p id="partner_info"></p>
										<p id="partner_addr"></p>
										<p id="partner_headcnt"></p>
										<p id="partner_email"></p>
										<p id="partner_purl"></p>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Ȯ��</button>
						</div>
					</div>

				</div>
			</div>

			<section id="tabs" class="project-tab">
				<div>
					<div class="row">
						<div class="col-md-12">
							<nav style="margin-top: 100px;">

								<ul class="nav nav-tabs" id="myTab" role="tablist">
									<li class="nav-item"><a id="firstNav" href="#nav-home"
										data-toggle="tab" onclick="tabtab('#nav-home')"
										class="nav-link active">���� ���</a></li>
									<li class="nav-item"><a id="secondNav" href="#nav-profile"
										data-toggle="tab" onclick="tabtab('#nav-profile')"
										class="nav-link">ä�� ����</a></li>
								</ul>
							</nav>

							<div class="tab-content" id="nav-tabContent">
								<div class="tab-pane fade show active" id="nav-home"
									role="tabpanel" aria-labelledby="nav-home-tab">
									<%--                ù��° ���� ���̺�                --%>
									<table class="table" cellspacing="0">
										<thead>
											<tr>
												<th>�̸�</th>
												<th>������</th>
												<th>�� �� ����</th>
												<%-- <c:forEach var="apList" items="${applyList}">
												<c:choose>
												<c:when test="${apList.partnerApplyState != '������'}">
												 --%>
												<th>��� ����</th>
												<%-- </c:when>
												</c:choose>
												</c:forEach>
											 --%>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="apList" items="${applyList}">
												<tr align="center">
													<td>${apList.memberVO.userName}</td>
													<td><a class="info" data-toggle="modal"
														href="#myModal"
														onclick="getResumeInfo('${apList.partnerApplyResumeID}');"><i
															class="far fa-id-card"></i></a></td>
													<c:choose>
														<c:when test="${apList.partnerApplyState == '������'}">
															<td><a style="text-decoration: underline" href="#"
																onclick="chk_passOrFail('${apList.memberVO.userId}','${apList.memberVO.userName}','${apList.partnerApplyPartnerID}');"><i
																	class="fas fa-user-check"></i></a></td>
															<td></td>
														</c:when>
														<c:when test="${apList.partnerApplyState == '�հ�   '}">
															<td style="color: blue;">${apList.partnerApplyState}</td>
															<td><a style="text-decoration: underline" href="#"
																onclick="chk_reset('${apList.memberVO.userId}','${apList.memberVO.userName}','${apList.partnerApplyPartnerID}');"><i
																	class="fas fa-user-edit"></i></a>
														</c:when>
														<c:otherwise>
															<td style="color: red;">${apList.partnerApplyState}</td>
															<td><a style="text-decoration: underline" href="#"
																onclick="chk_reset('${apList.memberVO.userId}','${apList.memberVO.userName}','${apList.partnerApplyPartnerID}');"><i
																	class="fas fa-user-edit"></i></a>
														</c:otherwise>
													</c:choose>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<div class="tab-pane fade" id="nav-profile" role="tabpanel"
									aria-labelledby="nav-profile-tab">
									<%--                �ι�° ���� ���̺�                --%>
									<table class="table" cellspacing="0">
										<thead>
											<tr>
												<th>�̸�</th>
												<th>�̷¼�</th>
												<th>ä������</th>
												<th>����</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="sugList" items="${suggestionList}">
												<tr>
													<td>${sugList.userName}</td>
													<td><a class="info" data-toggle="modal"
														href="#myModal"
														onclick="getResumeInfo('${sugList.resumeVO.resumeID}');"><i
															class="far fa-id-card"></i></a></td>

													<c:choose>
														<c:when
															test="${sugList.suggestionVO.partnerSuggestionAcception == null}">
															<td><a style="text-decoration: underline" href="#"
																onclick="suggestToUser('${sugList.userId}','${partner.partnerLicenseNum}','${sugList.userName}');return false;"><i
																	class="fas fa-hands-helping"></i></a></td>
														</c:when>
														<c:when
															test="${sugList.suggestionVO.partnerSuggestionAcception == '����'}">
															<td style="color: blue;">${sugList.suggestionVO.partnerSuggestionAcception}</td>
														</c:when>
														<c:when
															test="${sugList.suggestionVO.partnerSuggestionAcception == '���'}">
															<td style="color: green;">${sugList.suggestionVO.partnerSuggestionAcception}</td>
														</c:when>
														<c:otherwise>
															<td style="color: red;">${sugList.suggestionVO.partnerSuggestionAcception}</td>
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when
															test="${sugList.suggestionVO.partnerSuggestionPartnerD != null }">
															<td><a style="text-decoration: underline" href="#"
																onclick="deleteSuggestion('${sugList.userId}','${partner.partnerLicenseNum}');"><i
																	class="fas fa-user-times"></i></a></td>
														</c:when>
														<c:otherwise>
															<td></td>
														</c:otherwise>
													</c:choose>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
</body>
</html>

