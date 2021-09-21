package spring.boot.weather.forecasting.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Registration {
	@Id
	@Column(name = "user_id")
	private long rid;

	@Column(name = "user_name", nullable = false, length = 20)
	private String userName;

	@Column(nullable = false, length = 64)
	private String password;

	@Column(name = "re_enter_password", nullable = false, length = 64)
	private String reEnterPassword;
	

	public Registration() {
		super();
	}

	public Registration(long rid, String userName, String password, String reEnterPassword) {
		super();
		this.rid = rid;
		this.userName = userName;
		this.password = password;
		this.reEnterPassword = reEnterPassword;
	}

	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReEnterPassword() {
		return reEnterPassword;
	}

	public void setReEnterPassword(String reEnterPassword) {
		this.reEnterPassword = reEnterPassword;
	}

	@Override
	public String toString() {
		return "Registration [rid=" + rid + ", userName=" + userName + ", password=" + password + ", reEnterPassword="
				+ reEnterPassword + "]";
	}

}
