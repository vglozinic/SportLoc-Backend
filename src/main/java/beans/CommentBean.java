package beans;

import java.io.Serializable;

import helper.BeanHelper;

public class CommentBean extends BeanHelper implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	private int userId;
	private int commentatorId;
	private boolean vote;
	private boolean action;
	private String comment;
	private String commentator;
	
	public CommentBean() {}
	
	public CommentBean(int userId,
			boolean vote,
			String comment,
			int commentatorId,
			String commentator,
			boolean action) {
		super();
		this.userId = userId;
		this.vote = vote;
		this.comment = comment;
		this.commentatorId = commentatorId;
		this.commentator = commentator;
		this.action = action;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public boolean isVote() {
		return vote;
	}
	
	public void setVote(boolean vote) {
		this.vote = vote;
	}
	
	public String getComment() {
		return resolveNull(comment);
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public int getCommentatorId() {
		return commentatorId;
	}
	
	public void setCommentatorId(int commentatorId) {
		this.commentatorId = commentatorId;
	}
	
	public String getCommentator() {
		return resolveNull(commentator);
	}
	
	public void setCommentator(String commentator) {
		this.commentator = commentator;
	}
	
	public boolean isAction() {
		return action;
	}
	
	public void setAction(boolean action) {
		this.action = action;
	}

}
