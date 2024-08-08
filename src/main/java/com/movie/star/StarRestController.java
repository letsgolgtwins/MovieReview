package com.movie.star;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.star.BO.StarBO;

import jakarta.servlet.http.HttpSession;

@RestController
public class StarRestController {

	@Autowired
	private StarBO starBO;
	
	// 별점 매기기 toggle API
	// 인풋 파라미터 - movieId(PathVariable로 가져옴), userOriginId(session으로 가져옴), point(RequestParam으로 가져옴)
	// /star/{movieId}
	@PostMapping("/star/{movieId}")
	public Map<String, Object> StarToggle(
			@PathVariable(name = "movieId") int movieId, 
			@RequestParam("point") int point, HttpSession session) {
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
		starBO.starToggle(movieId, userOriginId, point);
		
		// 응답 JSON
		result.put("code", 200);
		result.put("message", "성공");
		return result;
	}
	
}
