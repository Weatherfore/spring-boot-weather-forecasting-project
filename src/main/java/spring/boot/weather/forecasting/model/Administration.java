package spring.boot.weather.forecasting.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "administration")
public class Administration {
	@Id
	@Column(name = "admin_id")
	private long adminId;
	
	@Column(name = "admin_name", nullable = false, length = 20)
	private String adminName;
	
	@Column(name = "admin_password", nullable = false, length = 64)
	private String adminPassword;
	

	public Administration() {
		super();
	}

	public Administration(long adminId, String adminName, String adminPassword) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPassword= adminPassword;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	@Override
	public String toString() {
		return "Administration [adminId=" + adminId + ", adminName=" + adminName + ", adminPassword=" + adminPassword
				+ "]";
	}

	
	
	
}
