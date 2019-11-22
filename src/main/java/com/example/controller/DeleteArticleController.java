package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

@Controller
public class DeleteArticleController {
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	
	
	@RequestMapping("/delete")
	public String deleteArticle(Integer id) {
		commentRepository.deleteByArticleId(id);
		articleRepository.deleteById(id);
		return "redirect:/article";
	}
	

}
