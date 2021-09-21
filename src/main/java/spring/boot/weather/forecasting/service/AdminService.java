package spring.boot.weather.forecasting.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logging.GlobalResources;
import spring.boot.weather.forecasting.exception.AdminNotFoundException;
import spring.boot.weather.forecasting.exception.IncorrectLoginCredentialsException;
import spring.boot.weather.forecasting.exception.NoSuchRegistrationException;
import spring.boot.weather.forecasting.model.Administration;
import spring.boot.weather.forecasting.model.Registration;
import spring.boot.weather.forecasting.repository.AdminRepository;
import spring.boot.weather.forecasting.repository.RegistrationRepository;

@Service
public class AdminService {
	private Logger Logger = GlobalResources.getLogger(AdminService.class);

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	RegistrationRepository registrationRepository;

	// Add admin

	public Administration addAdmin(Administration admin) {
		Logger.info("addAdmin");
		try {
			return adminRepository.save(admin);
		} catch (IllegalArgumentException iae) {
			Logger.error(iae.getMessage());
			return null;
		}
	}

	public Administration getAdminById(long adminId) throws AdminNotFoundException { // manually exception handling
		Logger.info("getAdminById " + adminId);
		Optional<Administration> optAdmin = adminRepository.findById(adminId);
		if (optAdmin.isEmpty())
			throw new AdminNotFoundException("No Such admin Id is present");
		else
			return optAdmin.get();
	}

	public List<Administration> getAllAdmin() {
		Logger.info("getAllAdmin");
		return adminRepository.findAll();
	}

	public String deleteAdminById(long adminId) throws AdminNotFoundException {
		if (adminRepository.existsById(adminId)) {
			adminRepository.deleteById(adminId);
			return "Admin successfully deleted";
		}
		Logger.error("The entered id is not exist");
		throw new AdminNotFoundException("No Such admin Id is present");
	}

	public Registration getUser(long rid) throws NoSuchRegistrationException {
		Registration user = null;
		if (registrationRepository.existsById(rid)) {
			user = registrationRepository.findById(rid).get();
			return user;
		}
		Logger.error("The entered id is not exist");
		throw new NoSuchRegistrationException("No Such User Id is present");
	}

	public List<Registration> getAllUsers() {
		Logger.info("getAllUsers");
		return registrationRepository.findAll();
	}

	public boolean loginAdmin(long adminId, String adminPassword, String adminName)
			throws IncorrectLoginCredentialsException {
		boolean result = false;
		if (adminRepository.existsById(adminId)
				&& adminRepository.findById(adminId).get().getAdminPassword().equals(adminPassword)
				&& adminRepository.findById(adminId).get().getAdminName().equals(adminName)) {

			Logger.info("Admin login is Successfull");
			result = true;
			return result;
		}
		Logger.error("details are incorrect");
		throw new IncorrectLoginCredentialsException("Invalid Credentials");	
	}
	
}
