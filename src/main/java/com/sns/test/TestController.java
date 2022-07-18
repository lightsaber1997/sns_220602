package com.sns.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	@RequestMapping("test0")
	@ResponseBody
	public String test0() {
		return "Hello World!";
	}
	
	@RequestMapping("test1")
	@ResponseBody
	public Map<String, Object> test1() {
		Map<String, Object> map = new HashMap<>();
		map.put("aaa", 1234);
		map.put("baa", 1234);
		map.put("caa", 1234);
		map.put("daa", 1234);
		return map;
	}
	
	@RequestMapping("test2")
	public String test2() {
		return "test/test";
	}
}
