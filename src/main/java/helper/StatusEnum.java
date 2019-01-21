package helper;

public enum StatusEnum {
	APPROVED(1),
	PENDING(2),
	BLOCKED(3);
	
	private int status;
	
	StatusEnum(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
	
}