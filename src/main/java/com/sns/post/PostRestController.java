package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("/post")
@RestController
public class PostRestController {

	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("text") String text,
			@RequestParam("file") MultipartFile file) {
		Map<String, Object> result = new HashMap<>();
		
		result.put("success", true);
		return result;
	}
}
