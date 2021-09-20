package spring.boot.weather.forecasting.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Registration {
	@Id
	@Column(name = "user_id")
	private long rid;

	@Column(name = "user_name", nullable = false, length = 20)
	private String name;// user_name

	@Column(nullable = false, length = 64)
	private String password;

	@Column(name = "re_enter_password", nullable = false, length = 64)
	private String reEnterPassword;
	
	//role - admin and user - create separate enum 

	public Registration() {
		super();
	}

	public Registration(long rid, String name, String password, String reEnterPassword) {
		super();
		this.rid = rid;
		this.name = name;
		this.password = password;
		this.reEnterPassword = reEnterPassword;
	}

	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "Registration [rid=" + rid + ", name=" + name + ", password=" + password + ", reEnterPassword="
				+ reEnterPassword + "]";
	}

}
