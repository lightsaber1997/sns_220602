package com.sns.like;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	public Like selectLikeByUserIdAndPostId(
			@Param("userId") int userId,
			@Param("postId") int postId);
	public void insertLike(
			@Param("userId") int userId, 
			@Param("postId") int postId);
	public void deleteLike(
			@Param("userId") int userId, 
			@Param("postId") int postId);
	public int getlikeCountByPostId(
			@Param("postId") int postId);
}
