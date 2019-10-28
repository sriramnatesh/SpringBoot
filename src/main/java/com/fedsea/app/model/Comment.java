package com.fedsea.app.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "comments")
@JsonIgnoreProperties
public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6414906438400877089L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long postId;
	@NotNull(message = "Commented by can't be null or blank.")
	private Long commentBy;
	@NotBlank(message = "Comment Message can't be null or blank.")
	private String comment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getCommentBy() {
		return commentBy;
	}

	public void setCommentBy(Long commentBy) {
		this.commentBy = commentBy;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
