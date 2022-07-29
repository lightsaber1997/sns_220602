package com.sns.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.LikeBO;
import com.sns.like.LikeDAO;

@Service
public class PostBO {
	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private CommentBO commentBO;
	
	public List<Post> selectAllPost() {
		return postDAO.selectAll();
	}
	
	public Post selectById(int id) {
		return postDAO.selectById(id);
	}
	
	public boolean addPost(int userId, String userLoginId, String content, MultipartFile file) {
		String filePath = null;
		
		
		if (file != null) {
			filePath = fileManagerService.saveFile(userLoginId, file);
		}
		
		postDAO.insertPost(userId, content, filePath);
			
		return true;
		

	}
	
	public void deletePostByPostIdAndUserId(int postId, int userId) {
		Post post = postDAO.selectById(postId);
		if (post == null || post.getUserId() != userId) {
			return;
		}
		
		// delete image file
		String imagePath = post.getImagePath();
		if (imagePath != null) {
			try {
				fileManagerService.deleteFile(imagePath);
			} catch (Exception e) {
				
			}
		}
		
		// delete the corresponding rows from the like table
		likeBO.deleteLikeByPostId(postId);
		
		// delete the corresponding rows from the comment table
		commentBO.deleteCommentByPostId(postId);
		
		// delete the corresponding row from the post table
		postDAO.deletePostById(postId);
		
	}
	
	
}
