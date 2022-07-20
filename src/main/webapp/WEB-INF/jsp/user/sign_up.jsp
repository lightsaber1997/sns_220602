<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="sign-up-box">
		<h1 class="m-4 font-weight-bold">회원가입</h1>
		<form id="signUpForm" method="post" action="/user/sign_up">
			<span class="sign-up-subject">ID</span>
			<%-- 인풋 옆에 중복확인 버튼을 옆에 붙이기 위해 div 만들고 d-flex --%>
			<div class="d-flex ml-3 mt-3">
				<input type="text" name="loginId" class="form-control col-6" placeholder="ID를 입력해주세요">
				<button type="button" id="loginIdCheckBtn" class="btn btn-success">중복확인</button>
			</div>
			
			<%-- 아이디 체크 결과 --%>
			<div class="ml-3 mb-3">
				<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
				<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용중인 ID입니다.</div>
				<div id="idCheckOk" class="small text-success d-none">사용 가능한 ID 입니다.</div>
			</div>
			
			<span class="sign-up-subject">Password</span>
			<div class="m-3">
				<input type="password" name="password" class="form-control col-6" placeholder="비밀번호를 입력하세요">
			</div>

			<span class="sign-up-subject">Confirm password</span>
			<div class="m-3">
				<input type="password" name="confirmPassword" class="form-control col-6" placeholder="비밀번호를 입력하세요">
			</div>

			<span class="sign-up-subject">Name</span>
			<div class="m-3">
				<input type="text" name="name" class="form-control col-6" placeholder="이름을 입력하세요">
			</div>

			<span class="sign-up-subject">이메일</span>
			<div class="m-3">
				<input type="text" name="email" class="form-control col-6" placeholder="이메일을 입력하세요">
			</div>
			
			<br>
			<div class="d-flex justify-content-center m-3">
				<button type="button" id="signUpBtn" class="btn btn-info">가입하기</button>
			</div>
		</form>
	</div>
</div>

<script>
$(document).ready(function() {
	let is_not_duplicate_id = false;
	let duplicate_check_complete = false;
	
	// detect the change in the login
	// [reference] The input event fires when the value of an 
	// <input>, <select>, or <textarea> element has been changed.
	$("input[name=loginId]").on("input", function() {
		duplicate_check_complete = false;
	});
	
	
	$("#loginIdCheckBtn").on("click", function() {
		// initialization
		is_not_duplicate_id = false;
		duplicate_check_complete = true;
		$("#idCheckLength").addClass("d-none");
		$("#idCheckDuplicated").addClass("d-none");
		$("#idCheckOk").addClass("d-none");
		
		
		let loginId = $("input[name=loginId]").val().trim();
		if (loginId.length <= 3) {
			$("#idCheckLength").removeClass("d-none")
			return;
		}

		
		
		$.ajax({
			url: "/user/is_duplicated_id",
			data: {"loginId": loginId},
			datatype: "json",
			success: function(data) {
				console.log(data);
				if (data["is_duplicate"] == true) {
					is_not_duplicate_id = false;
					$("#idCheckDuplicated").removeClass("d-none");
				}
				else {
					is_not_duplicate_id = true;
					$("#idCheckOk").removeClass("d-none");
				}
				
			}
		});
	});
	
	$("#signUpBtn").on("click", function() {
		let password = $("input[name=password]").val();
		let confirmPassword = $("input[name=confirmPassword]").val();
		let name = $("input[name=name]").val();
		let email = $("input[name=email]").val();
		
		// check if the user did id duplication check
		if (!duplicate_check_complete) {
			alert("아이디 중복 검사를 해주세요.");
			return;
		}
		
		// check if the id is not an duplicate id
		if (!is_not_duplicate_id) {
			return;
		}
		
		if (password.length == 0) {
			alert("비밀번호를 입력해주세요.");
			return;
		}
		
		if (confirmPassword.length == 0) {
			alert("confirmPassword를 입력해주세요.");
			return;
		}
		
		// check if the user's password and confirm password are same
		if (password != confirmPassword) {
			alert("password와 confirmPassword가 다릅니다. ");
			return;
		}
		
		$.ajax({
			url: "",
			data: {"loginId": loginId, 
				"password": password,
				"name": name,
				"email": email},
			datatype: "json",
			success: function(data) {
				if (data["success"] == true) {
					alert("success");
					// location.href = "/user/sign_in_view";
				}
				
				else {
					
				}
			}
		});
	});
		
});
</script>