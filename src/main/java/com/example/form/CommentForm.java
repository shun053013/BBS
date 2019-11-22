package com.example.form;

/**
 * 投稿情報のフォーム
 * 
 * @author hiraokayuri
 *
 */
public class CommentForm {
	/** 投稿者ID */
	private String articleId;
	/** 投稿者名 */
	private String name;
	/** 投稿者コメント */
	private String content;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleid(String articleId) {
		this.articleId = articleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CommentForm [articleid=" + articleId + ", name=" + name + ", content=" + content + "]";
	}

}
