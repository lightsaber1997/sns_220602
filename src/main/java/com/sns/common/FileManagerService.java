package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {
	public final static String FILE_UPLOAD_PATH = "D:\\lightsaber\\spring_project\\sns\\workspace\\images/";
	// public final static String FILE_UPLOAD_PATH = "C:\\eric_2022\\mega_it_web\\spring_project_220622\\sns_220602_file_storage/images/";
	
	public String saveFile(String userLoginId, MultipartFile file) {
		String directoryName = userLoginId + "_" + 
				System.currentTimeMillis() +"/";
		
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(filePath);
		if (!directory.mkdir()) {
			return null;
		}
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
			
			return "/images/" + directoryName + file.getOriginalFilename();
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	public void deleteFile(String dbImagePath) throws IOException {
		// "/images/test2_1658477877636/theron.jpg"
		String imagePath = dbImagePath.substring(8);
		Path path = Paths.get(FILE_UPLOAD_PATH + imagePath);
		if (Files.exists(path)) {
			Files.delete(path);
		}
		Path parent = path.getParent();
		if (Files.exists(parent)) {
			Files.delete(parent);
		}
	}
}
