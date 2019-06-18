package aa.user.model;

public class UserVO {

	private String userId;
	private String pass;
	private String name;
	private String alias;
	
	public UserVO() {
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", pass=" + pass + ", name=" + name + ", alias=" + alias + "]";
	}

	public UserVO(String userId, String pass, String name, String alias) {
		super();
		this.userId = userId;
		this.pass = pass;
		this.name = name;
		this.alias = alias;
	}

	

	
	
	
	
	
}
