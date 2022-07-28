package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeRestController {
	@Autowired
	private LikeBO likeBO;
	
	
	@RequestMapping("/like/{postId}")
	public Map<String, Object> like(
			@PathVariable int postId,
			HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<>();
		
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");
		
		likeBO.insertLike(userId, postId);
		map.put("success", true);
		return map;
	}
}
