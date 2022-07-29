package com.sns.like;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeBO {
	@Autowired
	private LikeDAO likeDAO;
	
	public void insertLike(int userId, int postId) {
		Like like = likeDAO.selectLikeByUserIdAndPostId(userId, postId);
		
		if (like == null) {
			likeDAO.insertLike(userId, postId);
		}
		
		else {
			likeDAO.deleteLike(userId, postId);
		}
		
	}
	
	public Like selectLikeByUserIdAndPostId(int userId, int postId) {
		return likeDAO.selectLikeByUserIdAndPostId(userId, postId);
	}
	
	public int getlikeCountByPostId(int postId) {
		return likeDAO.getlikeCountByPostId(postId);
	}
	
	public void deleteLikeByPostId(int postId) {
		likeDAO.deleteLikeByPostId(postId);
	}
}
