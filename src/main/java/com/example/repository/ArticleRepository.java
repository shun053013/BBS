package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * articlesテーブルを操作するレポジトリクラス.
 * 
 * @author hashimotoshinya
 *
 */
@Repository
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * articlesテーブルに対応したローマッパ.
	 * 
	 */
//	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
//		Article article = new Article();
//		article.setId(rs.getInt("id"));
//		article.setName(rs.getString("name"));
//		article.setContent(rs.getString("content"));
//
//		return article;
//
//	};

	/**
	 * articlesテーブルに対応したローマッパ.
	 */
	private static final ResultSetExtractor<List<Article>> ARTICLE_EXTRACTOR = (rs) -> {
		List<Article> articleList = new ArrayList<>();
		List<Comment> commentList = null;
		int preId = 0;

		while (rs.next()) {
			int nowId = rs.getInt("id");

			if (nowId != preId) {
				Article article = new Article();
				article.setId(nowId);
				article.setName(rs.getString("name"));
				article.setContent(rs.getString("content"));
				commentList = new ArrayList<>();
				article.setCommentList(commentList);

				articleList.add(article);
			}

			if(rs.getInt("com_id") != 0) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("com_id"));
				comment.setName(rs.getString("com_name"));
				comment.setContent(rs.getString("com_content"));
				comment.setArticleId(rs.getInt("article_id"));
				commentList.add(comment);
			}

			preId = nowId;
		}
		return articleList;
	};

	/**
	 * 記事情報を挿入します.
	 * 
	 * @param article 挿入したい記事情報
	 * 
	 */
	public void insert(Article article) {
		String sql = "insert into articles (name, content) values(:name, :content)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		template.update(sql, param);
	}

	/**
	 * 記事情報を全件検索します.
	 * 
	 * @return 検索された記事情報
	 */
//	public List<Article> findAll() {
//		String sql = "select id,name,content from articles order by id desc";
//		return template.query(sql, ARTICLE_ROW_MAPPER);
//	}

	/**
	 * 記事情報を全件検索します.
	 * 
	 * @return 検索された記事情報
	 */
	public List<Article> findAll() {
		String sql = "select a.id as id, a.name as name, a.content as content, c.id as com_id, c.name as com_name, c.content as com_content, c.article_id as article_id from articles a full join comments c on a.id = c.article_id order by a.id desc";
		return template.query(sql, ARTICLE_EXTRACTOR);
	}

	/**
	 * 記事を削除します.
	 * 
	 * @param id 削除したい記事Id
	 */
	public void deleteById(Integer id) {
		String sql = "delete from articles where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}

}