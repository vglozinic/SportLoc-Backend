package helper;

public enum ActionEnum {
	DEFAULT_EVENT(0),
	ENTER_EVENT(1),
	SEND_REQUEST(2),
	APPROVE_USER(3),
	BLOCK_USER(4),
	REMOVE_USER(5);
	
	private int action;
	
	ActionEnum (int action) {
		this.action = action;
	}
	
	public int getAction() {
		return action;
	}
	
	public static ActionEnum fromInt(int action) {
		for (ActionEnum operation : ActionEnum.values()) {
			if(ActionEnum.valueOf(operation.toString()).getAction() == action) {
				return operation; 
			}
		}
		return ActionEnum.DEFAULT_EVENT;
	}

}
