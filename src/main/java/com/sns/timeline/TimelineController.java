package com.sns.timeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.comment.Comment;
import com.sns.common.AlphaNumericRandomStringGenerator;
import com.sns.common.RandomStringGenerator;
import com.sns.post.Post;
import com.sns.post.PostBO;
import com.sns.timeline.Model.CardView;
import com.sns.user.User;
import com.sns.user.UserBO;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private TimelineBO timelineBO;
	
	@Autowired
	private RandomStringGenerator randomStringGenerator;
	
	@Autowired
	private Random random;
	
	@RequestMapping("/timeline_view")
	public String timeline(Model model, HttpServletRequest request) {

		
		model.addAttribute("viewName", "timeline/timeline");
		List<Post> listPost = postBO.selectAllPost(); 
		List<User> listUser = new ArrayList<>(listPost.size());
		for (int i=0; i < listPost.size(); i++) {
			User user = userBO.getUserById(listPost.get(i).getUserId());
			listUser.add(user);
		}
		int arrayLength = listPost.size();
		
		
		HttpSession session = request.getSession();
		String csrf_token = (String) session.getAttribute("csrf_token");
		if (csrf_token == null) {
			csrf_token = randomStringGenerator.getRandomString11(50);
			session.setAttribute("csrf_token", csrf_token);
		}
		
		Integer requestUserId = (Integer) session.getAttribute("userId");
		List<CardView> listCardView  = timelineBO.generateCardViewList(requestUserId);
		
		int listLength = listCardView.size();
		model.addAttribute("listLength", listLength);
		model.addAttribute("listCardView", listCardView);
		model.addAttribute("csrf_token", csrf_token);
		
		
		return "template/layout";
	}
}

