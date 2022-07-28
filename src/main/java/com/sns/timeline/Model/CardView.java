package com.sns.timeline.Model;

import java.util.List;

import com.sns.comment.Comment;
import com.sns.comment.CommentView;
import com.sns.post.Post;
import com.sns.user.User;

public class CardView {
	private Post post;
	private User user;
	private List<CommentView> commentList;
	// indicates whether the user pressed like for the post
	private boolean filledLike;
	// the number of like for this post
	private int likeCount;
	
	public CardView(Post post, User user, List<CommentView> commentList) {
		super();
		this.post = post;
		this.user = user;
		this.commentList = commentList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<CommentView> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentView> commentList) {
		this.commentList = commentList;
	}

	public boolean isFilledLike() {
		return filledLike;
	}

	public void setFilledLike(boolean filledLike) {
		this.filledLike = filledLike;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	
	
	
	
}
