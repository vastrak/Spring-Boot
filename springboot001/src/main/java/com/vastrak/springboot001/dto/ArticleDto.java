package com.vastrak.springboot001.dto;

import java.io.Serializable;


/**
 * 
 * @author Christian
 *
 */
public class ArticleDto implements Serializable {
	private Long article_id;
	private String title;
	private String body;
	private String userName;

	public ArticleDto() {
	}

	public Long getArticle_id() {
		return article_id;
	}

	public void setArticle_id(Long article_id) {
		this.article_id = article_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "ArticleDto [article_id=" + article_id + ", title=" + title + ", body=" + body + ", userName=" + userName
				+ "]";
	}
	
	
}
