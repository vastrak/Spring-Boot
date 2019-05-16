package com.vastrak.springboot001.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 
 * @author Christian
 *
 */
@Entity
public class User implements Serializable {
	@Id
	@GeneratedValue
	private Long user_id;
	private String name;
	private String email;
	@OneToMany(mappedBy = "user", cascade = { javax.persistence.CascadeType.ALL }, orphanRemoval = true)
	private List<Article> articles = new ArrayList();

	public User() {
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public void addArticle(Article article) {
		articles.add(article);
		article.setUser(this);
	}

	public void removeArticle(Article article) {
		article.setUser(null);
		articles.remove(article);
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = 31 * result + (email == null ? 0 : email.hashCode());
		result = 31 * result + (name == null ? 0 : name.hashCode());
		result = 31 * result + (user_id == null ? 0 : user_id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (email != null)
				return false;
		} else if (!email.equals(email))
			return false;
		if (name == null) {
			if (name != null)
				return false;
		} else if (!name.equals(name))
			return false;
		if (user_id == null) {
			if (user_id != null)
				return false;
		} else if (!user_id.equals(user_id))
			return false;
		return true;
	}

	public String toString() {
		return "User [user_id=" + user_id + 
				 ", name=" + name + 
				 ", email=" + email + 
				 ", articles=" + articles.size() + "]";
	}
}