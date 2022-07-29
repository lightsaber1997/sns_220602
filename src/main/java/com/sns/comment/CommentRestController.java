package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentRestController {
	@Autowired
	public CommentBO commentBO;
	
	@PostMapping("/comment/create")
	public Map<String, Object> createComment(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpServletRequest request,
			@RequestHeader Map<String, Object> header) {
		Map<String, Object> result = new HashMap<>();
		
		try {
			HttpSession session = request.getSession();
			
			// if user has not login
			Object userId_ = session.getAttribute("userId");
			if (userId_ == null) {
				result.put("success", false);
				return result;
			}
			int userId = (int) userId_;
			
			commentBO.insertComment(postId, userId, content);
			
			// check csrf token
			Object csrf_token_ = session.getAttribute("csrf_token");
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
			
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
		}
		
			
		return result;
		
	}
	
	@RequestMapping("/comment/delete")
	public Map<String, Object> deleteComment(
			@RequestParam("commentId") int commentId) {
		Map<String, Object> map = new HashMap<>();
		try {
			commentBO.deleteCommentById(commentId);
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
		}
		return map;
	}
}
