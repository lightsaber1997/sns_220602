package com.sns.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;

@Service
public class PostBO {
	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	private PostDAO postDAO;
	
	public boolean addPost(int userId, String userLoginId, String content, MultipartFile file) {
		String filePath = null;
		
		
		if (file != null) {
			filePath = fileManagerService.saveFile(userLoginId, file);
		}
		
		postDAO.insertPost(userId, content, filePath);
			
		return true;
		

	}
}