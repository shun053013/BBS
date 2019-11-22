package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

@Controller
public class InsertArticleController {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CommentRepository commentRepository;

	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}

	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}
	
	/**
	 * 記事情報を挿入します.
	 * 
	 * @param form  情報が格納された器
	 * @param model 情報を格納する器
	 * @return 入力画面及び記事・コメント表示
	 */
	@RequestMapping("/insert-article")
	public String insertArticle(@Validated ArticleForm form, BindingResult result, Model model) {
		System.out.println(result);
		if(result.hasErrors()) {
			return "redirect:/";
		}
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleRepository.insert(article);
		model.addAttribute("article", article);

		return "redirect:/article";
	}

}
