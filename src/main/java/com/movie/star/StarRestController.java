package com.movie.star;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.star.BO.StarBO;

import jakarta.servlet.http.HttpSession;

@RestController
public class StarRestController {

	@Autowired
	private StarBO starBO;
	
	// 별점 매기기
	// 1번별 클릭
	// /star1/{movieId}
	@GetMapping("/star1/{movieId}")
	public Map<String, Object> StarToggle1(
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
		starBO.StarToggle1(movieId, userOriginId);
		
		// 응답 JSON
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
	
	// 2번별 클릭
	// /star2/{movieId}
	@GetMapping("/star2/{movieId}")
	public Map<String, Object> StarToggle2(
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
		starBO.StarToggle2(movieId, userOriginId);
		
		// 응답 JSON
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
	
	// 3번별 클릭
	// /star3/{movieId}
	@GetMapping("/star3/{movieId}")
	public Map<String, Object> StarToggle3(
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
		starBO.StarToggle3(movieId, userOriginId);
		
		// 응답 JSON
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
	
	// 4번별 클릭
	// /star4/{movieId}
	@GetMapping("/star4/{movieId}")
	public Map<String, Object> StarToggle4(
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
		starBO.StarToggle4(movieId, userOriginId);
		
		// 응답 JSON
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
	
	// 5번별 클릭
	// /star5/{movieId}
	@GetMapping("/star5/{movieId}")
	public Map<String, Object> StarToggle5(
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
		starBO.StarToggle5(movieId, userOriginId);
		
		// 응답 JSON
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
}
