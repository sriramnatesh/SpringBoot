package com.fedsea.app.dto;

public class LikesResponse {
	private int totalLikes;
	private int totalUnLikes;
	private ReactionDto reactions;

	public int getTotalLikes() {
		return totalLikes;
	}

	public void setTotalLikes(int totalLikes) {
		this.totalLikes = totalLikes;
	}

	public int getTotalUnLikes() {
		return totalUnLikes;
	}

	public void setTotalUnLikes(int totalUnLikes) {
		this.totalUnLikes = totalUnLikes;
	}

	public ReactionDto getReactions() {
		return reactions;
	}

	public void setReactions(ReactionDto reactions) {
		this.reactions = reactions;
	}
}
