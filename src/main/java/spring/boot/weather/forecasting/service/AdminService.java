package spring.boot.weather.forecasting.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logging.GlobalResources;
import spring.boot.weather.forecasting.exception.AdminNotFoundException;
import spring.boot.weather.forecasting.exception.EmptyRecordException;
import spring.boot.weather.forecasting.exception.IncorrectLoginCredentialsException;
import spring.boot.weather.forecasting.exception.InvalidFieldException;
import spring.boot.weather.forecasting.exception.NoSuchRegistrationException;
import spring.boot.weather.forecasting.exception.NotAbleToUpdateException;
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

	// Administration registration
	@Transactional
	public boolean addAdmin(Administration admin) throws InvalidFieldException {
		if(!adminRepository.existsById(admin.getAdminId())) {
		if (admin.getAdminName() != null && admin.getAdminId() != 0L && admin.getAdminPassword() != null) {
			boolean result = false;
			String name = admin.getAdminName();

			String regex = "^[A-Za-z ]+";
			if (name.matches(regex)) {
				admin = adminRepository.save(admin);
				result = true;
				Logger.info("Admin is added successfully");
				return result;
			}
			Logger.error("Incorrect details");
			throw new InvalidFieldException("Not able to add user");

		}
		throw new InvalidFieldException("Fields are empty");
	}
		throw new InvalidFieldException("Id Already Exists");
	}

	// get Administration details by adminId
	public Administration getAdminById(long adminId) throws AdminNotFoundException {
		Logger.info("getAdminById " + adminId);
		Optional<Administration> optAdmin = adminRepository.findById(adminId);
		if (optAdmin.isEmpty())
			throw new AdminNotFoundException("No Such admin Id is present");
		else
			return optAdmin.get();
	}

	// Get All the Administration
	public List<Administration> getAllAdmin() throws EmptyRecordException {
		if (adminRepository.count() != 0) {
			Logger.info("getAllAdmin");
			return adminRepository.findAll();
		}
		Logger.error("Empty record found");
		throw new EmptyRecordException("Empty Record Found");
	}

	// Delete administration using adminId
	public String deleteAdminById(long adminId) throws AdminNotFoundException {
		if (adminRepository.existsById(adminId)) {
			adminRepository.deleteById(adminId);
			return "The Admin was successfully deleted";
		}
		Logger.error("The entered id is not exist");
		throw new AdminNotFoundException("No Such admin Id is present");
	}

	// Get User details from Administration
	public Registration getUser(long rid) throws NoSuchRegistrationException {
		Registration user = null;
		if (registrationRepository.existsById(rid)) {
			user = registrationRepository.findById(rid).get();
			return user;
		}
		Logger.error("The entered id is not exist");
		throw new NoSuchRegistrationException("No Such User Id is present");
	}

	public String deleteUser(long rid) throws NoSuchRegistrationException {
		if (registrationRepository.existsById(rid)) {
			registrationRepository.deleteById(rid);
			return "Registration successfully deleted";
		}
		Logger.error("The entered id is not exist");
		throw new NoSuchRegistrationException("No Such User Id is present");
	}

	// Get All the user details from Administration
	public List<Registration> getAllUsers() throws EmptyRecordException{
		if(registrationRepository.count()!=0L) {
		Logger.info("getAllUsers");
		return registrationRepository.findAll();
	}
		Logger.error("Empty record found");
		throw new EmptyRecordException("Empty record found");
  }

	// Administration Login
	public Administration loginAdmin(long adminId, String adminPassword, String adminName)
			throws IncorrectLoginCredentialsException {
		if (adminRepository.existsById(adminId)
				&& adminRepository.findById(adminId).get().getAdminPassword().equals(adminPassword)
				&& adminRepository.findById(adminId).get().getAdminName().equals(adminName)){
			Logger.info("Admin login is Successfull");
			Administration admin = adminRepository.findById(adminId).get();
			return admin;
		}
		Logger.error("details are incorrect");
		throw new IncorrectLoginCredentialsException("Invalid Credentials");
	}

	public Administration updatingAdmin(Administration admin) throws NotAbleToUpdateException {
		if (adminRepository.existsById(admin.getAdminId())) {
			if (admin.getAdminId() == 0L || admin.getAdminPassword() == "" || admin.getAdminName() == "") {
				throw new NotAbleToUpdateException("Invalid fields");
			} else
				return adminRepository.save(admin);
		} else {
			Logger.error("The entered id is not exist");
			throw new NotAbleToUpdateException("No Such admin Id is present");
		}
	}

}
