package com.movie.star;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.star.domain.Star;

import jakarta.servlet.http.HttpSession;

@Controller
public class StarController {

	// Star에 담긴 각종 정보들(누가, 어느 영화에, 몇 점을) model로 보내기
	@GetMapping("/star/startoggle")
	public String starToggle(Model model) {
		
		List<Star> star = new ArrayList<>();
		
		// model에 Star 정보 담기
		model.addAttribute("starInfo", star);
		
		return "movie/movieDetailView";
	}
}
