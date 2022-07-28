package com.sns.timeline;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.Comment;
import com.sns.comment.CommentBO;
import com.sns.comment.CommentDAO;
import com.sns.comment.CommentView;
import com.sns.like.Like;
import com.sns.like.LikeBO;
import com.sns.post.Post;
import com.sns.post.PostBO;
import com.sns.timeline.Model.CardView;
import com.sns.user.User;
import com.sns.user.UserBO;

@Service
public class TimelineBO {
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private LikeBO likeBO;
	
	/**
	 * Retuns a list of Cardview objects.
	 * The list of comments are ordered so that the most recent ones are
	 * in the front
	 * @return a list of Cardview objects.
	 */
	public List<CardView> generateCardViewList(Integer requestUserId) {
		List<CardView> listCardView = new ArrayList<>();
		List<Post> listPost = postBO.selectAllPost();
		CardView cardView;
		
		for (Post post: listPost) {
			int postId = post.getId();
			int userId = post.getUserId();
			List<CommentView> listCommentView = commentBO.selectCommentViewByPostId(postId);
			User user = userBO.getUserById(userId);
			cardView = new CardView(post, user, listCommentView);
			
			cardView.setFilledLike(false);
			if (requestUserId != null) {
				Like like = likeBO.selectLikeByUserIdAndPostId(requestUserId, postId);
				
				if (like != null) {
					cardView.setFilledLike(true);
				}
			}
			
			int likeCount = likeBO.getlikeCountByPostId(postId);
			cardView.setLikeCount(likeCount);
			
			listCardView.add(cardView);
		}
		
		
		return listCardView;
	}
}
