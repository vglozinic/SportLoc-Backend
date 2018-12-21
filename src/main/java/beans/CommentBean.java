package beans;

public class CommentBean {
	
	public static final long serialVersionUID = 1L;
	
	private int userId;
	private int commentatorId;
	private boolean vote;
	private String comment;
	private String commentator;
	
	public CommentBean() {}
	
	public CommentBean(int userId,
			boolean vote,
			String comment,
			int commentatorId,
			String commentator) {
		super();
		this.userId = userId;
		this.vote = vote;
		this.comment = comment;
		this.commentatorId = commentatorId;
		this.commentator = commentator;
	}
	
	private String resolveNull(String string) {
		if(string == null) {
			string = "";
		}
		return string;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public boolean getVote() {
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

}
