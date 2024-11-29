package com.fullstack.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fullstack.springboot.dto.MovieDTO;
import com.fullstack.springboot.dto.MovieImageDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.service.MovieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class MovieController {
	
	private final MovieService movieService;
	
	@GetMapping("/register")
	public void register() {
	}
	
	@PostMapping("/register")
	public String register(MovieDTO movieDTO, RedirectAttributes redirectAttributes) {
		log.error(movieDTO);
		Long mno = movieService.register(movieDTO);
		
		redirectAttributes.addFlashAttribute("msg", mno);
		
		return "redirect:/movie/list";
	}
	
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		
		log.warn("pageRequestDTO : "+pageRequestDTO);
		
		model.addAttribute("result", movieService.getList(pageRequestDTO));
	}
	
	@GetMapping({"/read", "/modify"})
	public void read(@RequestParam("mno") Long mno, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model) {
		MovieDTO movieDTO = movieService.getMovie(mno);
		model.addAttribute("dto",movieDTO);
	}
	
}
