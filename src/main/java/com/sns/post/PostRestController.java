package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("/post")
@RestController
public class PostRestController {
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("text") String text,
			@RequestParam(value="file", required=false) MultipartFile file,
			HttpServletRequest request,
			@RequestHeader Map<String, Object> header) {
		Map<String, Object> result = new HashMap<>();
		
		HttpSession session = request.getSession();
		Object userId_ = session.getAttribute("userId");
		Object userLoginId_ = session.getAttribute("userLoginId");
		Object csrf_token_ = session.getAttribute("csrf_token");
		
		
		if (userId_ == null) {
			result.put("success", false);
			return result;
		}
		
		// csrf token check
		Object request_csrf_token_ = header.get("csrf_token_value");
		if (csrf_token_ == null || request_csrf_token_ == null) {
			result.put("success", false);
			return result;
		}
		String csrf_token = (String) csrf_token_;
		String request_csrf_token = (String) request_csrf_token_;
		
		if (!csrf_token.equals(request_csrf_token)) {
			result.put("success", false);
			return result;
		}
		
		int userId = (int) userId_;
		String userLoginId =(String) userLoginId_;
		
		postBO.addPost(userId, userLoginId, text, file);
		
		result.put("success", true);
		return result;
	}
}
