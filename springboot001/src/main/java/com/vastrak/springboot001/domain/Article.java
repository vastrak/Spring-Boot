package com.vastrak.springboot001.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 
 * @author Christian
 *
 */
@Entity
public class Article
{
  @Id
  @GeneratedValue
  private Long article_id;
  private String title;
  private String body;
  @ManyToOne(fetch=javax.persistence.FetchType.EAGER) 
  @JoinColumn(name="user_id")
  private User user;
  
  public Article() {}
  
  public Long getArticle_id()
  {
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
  
  public User getUser() {
    return user;
  }
  
  public void setUser(User user) {
    this.user = user;
  }
  
  public int hashCode()
  {
    int prime = 31;
    int result = 1;
    result = 31 * result + (article_id == null ? 0 : article_id.hashCode());
    result = 31 * result + (title == null ? 0 : title.hashCode());
    result = 31 * result + (user == null ? 0 : user.hashCode());
    return result;
  }
  
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Article other = (Article)obj;
    if (article_id == null) {
      if (article_id != null)
        return false;
    } else if (!article_id.equals(article_id))
      return false;
    if (title == null) {
      if (title != null)
        return false;
    } else if (!title.equals(title))
      return false;
    return true;
  }
  

  public String toString()
  {
    return "Article id: " + article_id + 
    		" Title: " + title + 
    		" Body:  " + body + 
    		" User:  " + (user == null ? null : user.getName());
  }
}