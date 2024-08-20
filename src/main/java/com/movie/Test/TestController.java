package com.movie.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.movie.Review.mapper.ReviewMapper;

@RequestMapping("/test")
@Controller
public class TestController {

	// 테스트1 - 완료
	// http://localhost/test/1
	@RequestMapping("/1")
	@ResponseBody
	public String test1() {
		return "hello World!";
	}
	
	// 테스트2 - 완료
	// http://localhost/test/2
	@RequestMapping("/2")
	@ResponseBody
	public Map<String, Object> test2() {
		Map<String, Object> map = new HashMap<>();
		map.put("테스트1", 1);
		map.put("테스트2", 2);
		return map;
	}
	
	// 테스트3 - 완료
	// http://localhost/test/3
	@RequestMapping("/3")
	public String test3() {
		return "test/test3";
	}
	
	@Autowired
	private ReviewMapper reviewMapper;
	
	// 테스트4 -
	// http://localhost/test/4
	@ResponseBody
	@GetMapping("/4")
	public List<Map<String, Object>> test4() {
		return reviewMapper.selectReviewListTest();
	}
	
	// 깃허브에 잘 올라가나 테스트
}
