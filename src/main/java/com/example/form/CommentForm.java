package com.example.form;

/**
 * 投稿情報のフォーム
 * 
 * @author hiraokayuri
 *
 */
public class CommentForm {
	/** 投稿者ID */
	private String articleid;
	/** 投稿者名 */
	private String name;
	/** 投稿者コメント */
	private String content;

	public String getArticleid() {
		return articleid;
	}

	public void setArticleid(String articleid) {
		this.articleid = articleid;
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
		return "CommentForm [articleid=" + articleid + ", name=" + name + ", content=" + content + "]";
	}

}
