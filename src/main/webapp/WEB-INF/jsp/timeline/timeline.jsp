<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="d-flex justify-content-center">
	<div class="contents-box">
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea> 
			<div class="d-flex justify-content-between">
				<div class="d-flex">
					<input type="file" id="file" class="d-none" accept=".gif,.jpg,.png,.jpeg">
					<div id="fileUploadBtn">
						<img src="https://cdn-icons-png.flaticon.com/512/44/44289.png" alt="imagae selection" width="35">
					</div>
					<%-- shows the name of the uploaded image --%>
					<div id="fileName" class="ml-1">
						
					</div>
				</div>
				<button type="button" class="btn btn-info">게시</button>
			</div>
		</div>
		
	</div>
	
</div>


<script>
$(document).ready(function() {
	
	// file upload image click
	$("#fileUploadBtn").on({
		click: function() {
			$("#file").click();
		},
		
	});
	
	
	$("#file").on("change", function(e) {
		let file_name = e.target.files[0].name;
		let allowed_extensions = ['jpg', 'jpeg', 'png', 'gif']
		let is_allowed_extension = check_file_extension(file_name, allowed_extensions);
		if (!is_allowed_extension) {
			$(this).val("");
			alert("이미지 파일만 업로드 할 수 있습니다. ");
			file_name = "";
		}
		
		
		$("#fileName").text(file_name);
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
</script>
    
