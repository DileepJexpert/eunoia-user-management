package com.eunoia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Author
{
  private Long authorId;
  private String authorName;
	
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  public Long getAuthorId()
	{
		return authorId;
	}
	public void setAuthorId(Long authorId)
	{
		this.authorId = authorId;
	}
	
	@Column(name="author_name")
	public String getAuthorName()
	{
		return authorName;
	}
	public void setAuthorName(String authorName)
	{
		this.authorName = authorName;
	}
}

