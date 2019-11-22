package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Comment;
import com.example.form.CommentForm;
import com.example.repository.CommentRepository;

/**
 * コメント情報を登録する.
 * 
 * @param commentForm リクエストパラメーター
 * @param model       リクエストスコープ
 * @return インデックス画面
 */
@Controller
@RequestMapping
public class InsertComentController {

	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();

	}
	@Autowired
	private CommentRepository commentRepository;

	
	@RequestMapping("/insertComment")
	public String insertComment(CommentForm commentForm, Model model) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentForm, comment);
		comment.setArticleId(Integer.parseInt(commentForm.getArticleid()));
		commentRepository.insert(comment);
		return "redirect:/article";

	}

}
