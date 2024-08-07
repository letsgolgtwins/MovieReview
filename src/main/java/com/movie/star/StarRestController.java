package com.movie.star;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.star.BO.StarBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/star")
@RestController
public class StarRestController {

	@Autowired
	private StarBO starBO;
	
	// 별점 toggle
	// /star/{movieId}
	@GetMapping("/{movieId}")
	public Map<String, Object> StarToggle(
			@PathVariable(name = "movieId") int movieId, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		// 로그인 여부 확인
		String userId = (String) session.getAttribute("userId");
		if (userId == null) { // 비로그인 상태
			result.put("code", 403);
			result.put("error_message", "로그인을 해주세요.");
			return result;
		}
		// 여기서부턴 로그인이 된 상태

		// session에서 userOriginId 가져오기
		Integer userOriginId = (Integer) session.getAttribute("userOriginId");
		
		// 별점 toggle
		starBO.starToggle(movieId, userOriginId);
		
		// 응답 JSON
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
}
