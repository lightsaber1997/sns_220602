package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
			HttpServletRequest request) {
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
			
			
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
		}
		
			
		return result;
		
	}
}
