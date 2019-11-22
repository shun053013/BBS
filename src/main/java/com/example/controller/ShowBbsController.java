package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 掲示板の記事一覧の表示を行うコントローラクラス.
 * @author yuichi
 *
 */
@Controller
public class ShowBbsController {
	
	/**
	 * 掲示板のトップページにフォワードするメソッド.
	 * @return 掲示板のトップページ
	 */
	@RequestMapping("/")
	public String index() {
		return "index.html";
	}
	
	
}
