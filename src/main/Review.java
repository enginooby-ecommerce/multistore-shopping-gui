package main;

public class Review {
	private int reviewId;
	private String title;
	private String content;
	private int customerId;
	private int itemId;
	private int rate;
	private Like like;
	private Dislike dislike;
	
	public Review() {
		
	}

	public Review(int reviewId,String title, String content, int customerId, int itemId, int rate) {
		this.reviewId = reviewId;
		this.title = title;
		this.content = content;
		this.customerId = customerId;
		this.itemId = itemId;
		this.rate = rate;
	}



	public String getContent() {
		return content;
	}

	public int getCustomerId() {
		return customerId;
	}

	public int getItemId() {
		return itemId;
	}

	public int getRate() {
		return rate;
	}

	public Like getLike() {
		return like;
	}

	public Dislike getDislike() {
		return dislike;
	}

	public void setLike(Like like) {
		this.like = like;
	}

	public void setDislike(Dislike dislike) {
		this.dislike = dislike;
	}

	public String getTitle() {
		return title;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	
	

}
