<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="contents-box">
		<div class="write-box border rounded m-3">
			<div id="uploadImageWrapper" >
				<img class="d-none img-fluid" id="uploadImage">
			</div>
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요"
				class="w-100 border-0"></textarea>
			<div class="d-flex justify-content-between">
				<div class="d-flex">
					<input type="file" id="file" class="d-none"
						accept=".gif,.jpg,.png,.jpeg">
					<div id="fileUploadBtn">
						<img src="https://cdn-icons-png.flaticon.com/512/44/44289.png"
							alt="imagae selection" width="35">
					</div>
					<%-- shows the name of the uploaded image --%>
					<div id="fileName" class="ml-1"></div>
				</div>
				<button type="button" id="submit_post" class="btn btn-info">게시</button>
			</div>
		</div>
		
		<c:forEach var="i" begin="0" end="${listLength-1}">
			<%-- 타임라인 영역 --%>
			<div class="timeline-box my-5">
				<%-- 카드 마다 영역을 border로 나눔 --%>
				<div class="card border rounded mt-3">
					<%-- 글쓴이 아이디, 삭제를 위한 ...버튼 : 이 둘을 한 행에 멀리 떨어뜨려 나타내기 위해 d-flex, between --%>
					<div class="p-2 d-flex justify-content-between">
						<span class="font-weight-bold">${listCardView[i].user.name}</span>
						
						<c:if test="${listCardView[i].user.id eq userId}">
							<%-- 삭제 모달을 뛰우기 위한 ... 버튼 --%>
							<a href="#" class="more-btn"  data-bs-toggle="modal" data-bs-target="#exampleModal" data-post-id="${listCardView[i].post.id}"> <img
								src="https://www.iconninja.com/files/860/824/939/more-icon.png"
								width="30">
							</a>
						</c:if>

					</div>
	
					<%-- 카드 이미지 --%>
					<div class="card-img">
						
							<img
								src="${listCardView[i].post.imagePath}"
								class="w-100" alt="이미지">
					</div>
	
					<%-- 좋아요 --%>
					<div class="card-like m-3">
						<div class="like-btn d-flex justify-content-start"> 
						<div class="pr-5 saveLikeButton" data-post-id="${listCardView[i].post.id}">
						
						<c:choose>
							<c:when test="${listCardView[i].filledLike}">
								<img 
								src="https://www.iconninja.com/files/527/809/128/heart-icon.png"
								width="18px" height="18px" alt="empty heart"> 
							</c:when>
							<c:otherwise>
								<img 
								src="https://www.iconninja.com/files/214/518/441/heart-icon.png"
								width="18px" height="18px" alt="empty heart"> 
							</c:otherwise>
						</c:choose>
						
							
						</div>
						<div>
						좋아요 ${listCardView[i].likeCount}개
						</div>
							
						</div>
					</div>
	
					<%-- 글(post) --%>
					<div class="card-post m-3">
						<span class="fw-bold">${listCardView[i].user.name}</span> <span>${listCardView[i].post.content}</span>
					</div>
	
					<%-- 댓글(comment) --%>
					<div class="card-comment-desc border-bottom">
						<div class="ml-3 mb-1 font-weight-bold">댓글</div>
					</div>
					<div class="card-comment-list m-2">
						<%-- 댓글 목록 --%>
						<div class="card-comment m-1">
							<span class="font-weight-bold">댓글쓰니 : </span> <span>댓글 내용</span>
							
							<c:forEach var="comment" items="${listCardView[i].commentList}" varStatus="status">
								<div class="d-flex">
									<p class="col-10"><span class="fw-bold">${comment.userName}</span> ${comment.content}</p>
									<c:if test="${userId eq comment.userId}">
									<%-- 댓글 삭제 --%>
										<div href="#" class="commentDelBtn"
											data-comment-id="${comment.id}"> <img
											src="https://www.iconninja.com/files/603/22/506/x-icon.png"
											width="10px" height="10px">
										</div>	
									</c:if>
									
								</div>

							</c:forEach>
							
								
						</div>
					</div>
					
					<%-- 댓글 쓰기 --%>
					<div class="comment-write d-flex">
						<input type="text" class="form-control commentText" placeholder="댓글달기">
						<button type="button" class="btn btn-light col-3 commentBtn" data-post-id="${listCardView[i].post.id}">게시</button>
					</div>
				</div>
	
			</div>
		</c:forEach>

			

	</div>



</div>


<input type="hidden" id="csrf_token" name="csrf_token" value="${csrf_token}">

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
  Launch demo modal
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-post-id="">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Delete Post</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        게시물을 삭제하겠습니까?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
        <button type="button" class="btn btn-primary delPost">확인</button>
      </div>
    </div>
  </div>
</div>



<script>
$(document).ready(
	function() {
		$("#writeTextArea").css("outline", "none");
		$("#writeTextArea").css("resize", "none");
		$(".saveLikeButton").css("cursor", "pointer");

		// file upload image click
		$("#fileUploadBtn").on({
			click : function() {
				$("#file").click();
			}

		});

		$("#file").on(
				"change",
				function(e) {
					let file_name = e.target.files[0].name;
					let allowed_extensions = [ 'jpg', 'jpeg', 'png',
							'gif' ]
					let is_allowed_extension = check_file_extension(
							file_name, allowed_extensions);
					if (!is_allowed_extension) {
						$(this).val("");
						alert("이미지 파일만 업로드 할 수 있습니다. ");
						file_name = "";
					}
					
					let file = this.files[0];
					
					$("#fileName").text(file_name);
					$("#uploadImage").removeClass("d-none");
					$("#uploadImage").attr("src", URL.createObjectURL(file));
					
					
				});
		
	
		
		$("#submit_post").on("click", function() {
			// console.log("clicked");

			// Send a file using ajax
			// reference: https://stackoverflow.com/questions/2320069/jquery-ajax-file-upload
			let form_data = new FormData();
			form_data.append("text", $("#writeTextArea").val());
			form_data.append("file", $("#file")[0].files[0]);
			
			let csrf_token_value = $("#csrf_token").val();
			$.ajax({
				url : "/post/create",
				method : "POST",
				data : form_data,
				processData : false,
				contentType : false,
				beforeSend: function(xhr) {
					xhr.setRequestHeader("csrf_token_value", csrf_token_value);
				},
				success : function(data) {
					console.log(data);
					// reload the page
					location.reload();
				}
			});
		});
		
		$(".commentBtn").on("click", function() {
			let postId = $(this).data("post-id");
			let content = $(this).siblings("input").val().trim();
			
			let csrf_token_value = $("#csrf_token").val();
			
			$.ajax({
				url: "/comment/create",
				method: "POST",
				data: {"postId": postId, "content": content},
				beforeSend: function(xhr) {
					xhr.setRequestHeader("csrf_token_value", csrf_token_value);
				},
				success: function(data) {
					console.log(data);
					if (data["success"] == true) {
						location.reload();
					}
				}
			});
		});
		
		$(".commentText").on("keypress", function(e) {
			if (e.key == "Enter") {
				console.log("ddd");
				$(".commentBtn").trigger("click");
			}
		});
		
		$(".saveLikeButton").on("click", function() {
			let postId = $(this).data("post-id");
			$.ajax({
				url: "/like/" + postId,
				success: function(data) {
					console.log(data);
					if (data["success"] == true) {
						location.reload();
					}
				}
			});
		});
		
		$(".more-btn").on("click", function(e) {
			e.preventDefault();
			
			let postId = $(this).data("post-id");
			
			$("#exampleModal").data("post-id", postId);
		});
		
		$("#exampleModal .delPost").on("click", function(e) {
			let postId = $("#exampleModal").data("post-id");
			// console.log(postId);
			$.ajax({
				type: "DELETE",
				url: "/post/delete",
				data: {"postId": postId},
				success: function(data) {
					console.log(data);
					if (data["success"] == true) {
						location.reload(true);
					}
				}
			});
		});
		
		$(".commentDelBtn").on("click", function() {
			let commentId = $(this).data("comment-id");
			$.ajax({
				url: "/comment/delete",
				method: "POST",
				data: {"commentId": commentId},
				success: function(data) {
					if (data["success"] == true) {
						location.reload(true);
					}
				}
			});
		});
});

	/**
	 * Returns whether the file_path has an allowed file extension
	 * file_path: file path of the file to be submitted via form
	 * allowed_extension_list: string list containing allowed file extensions
	 * ex. ["c", "py", "java"] 
	 */
	function check_file_extension(file_path, allowed_extension_list) {
		let temp = file_path.split(".").pop().toLowerCase();
		return allowed_extension_list.includes(temp);
	}
</script>

