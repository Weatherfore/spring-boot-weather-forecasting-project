package spring.boot.weather.forecasting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logging.GlobalResources;
import spring.boot.weather.forecasting.exception.IncorrectLoginCredentialsException;
import spring.boot.weather.forecasting.exception.InvalidFieldException;
import spring.boot.weather.forecasting.exception.NoSuchRegistrationException;
import spring.boot.weather.forecasting.exception.NotAbleToUpdateException;
import spring.boot.weather.forecasting.model.Registration;
import spring.boot.weather.forecasting.repository.RegistrationRepository;

import javax.transaction.Transactional;

import org.slf4j.Logger;

@Service
public class RegistrationService {

	private Logger Logger = GlobalResources.getLogger(RegistrationService.class);

	@Autowired
	RegistrationRepository registrationRepository;

	// User Registration
	@Transactional
	public boolean addUser(Registration newUser) throws InvalidFieldException {
		Logger.info(newUser.toString());
		if(!registrationRepository.existsById(newUser.getRid())) {
		if (newUser.getUserName() != null && newUser.getRid() != 0L && newUser.getPassword() != null
				&& newUser.getReEnterPassword() != null) {
			boolean result = false;
			String name = newUser.getUserName();

			String regex = "^[A-Za-z ]+";
			if (name.matches(regex)) {
				newUser = registrationRepository.save(newUser);
				result = true;
				if (newUser.getPassword().equals(newUser.getReEnterPassword())) {
					Logger.info("User is added successfully");
					return result;
				}

				if (!newUser.getPassword().equals(newUser.getReEnterPassword())) {
					throw new InvalidFieldException("Password is not matching");

				}
			}
			Logger.error("Incorrect details");
			throw new InvalidFieldException("Not able to add user");

		}
		throw new InvalidFieldException("Fields are empty");
	}
		throw new InvalidFieldException("Id already exists");

	}
	public Registration loginUser(long rid, String password, String userName, String reEnterPassword)
			throws IncorrectLoginCredentialsException {
		if (registrationRepository.existsById(rid)
				&& registrationRepository.findById(rid).get().getPassword().equals(password)
				&& registrationRepository.findById(rid).get().getUserName().equals(userName)
				&& registrationRepository.findById(rid).get().getReEnterPassword().equals(reEnterPassword)) {
			Registration user = registrationRepository.findById(rid).get();
			Logger.info("User login is Successfull");
			return user;
		}
		Logger.error("details are incorrect");
		throw new IncorrectLoginCredentialsException("Invalid Credentials");

	}

	
	// GetUserById
	public Registration getUser(long rid) throws NoSuchRegistrationException {
		Registration user = null;
		if (registrationRepository.existsById(rid)) {
			user = registrationRepository.findById(rid).get();
			return user;
		}
		Logger.error("The entered id is not exist");
		throw new NoSuchRegistrationException("No Such User Id is present");
	}

	// DeleteUserById
	public String deleteUser(long rid) throws NoSuchRegistrationException {
		if (registrationRepository.existsById(rid)) {
			registrationRepository.deleteById(rid);
			return "Registration successfully deleted";
		}
		Logger.error("The entered id is not exist");
		throw new NoSuchRegistrationException("No Such User Id is present");
	}

	public Registration updatingUser(Registration user) throws NotAbleToUpdateException {
		if (registrationRepository.existsById(user.getRid())) {
 			if (user.getUserName() == "" || user.getPassword() == "" || user.getReEnterPassword() == "") {
				throw new NotAbleToUpdateException("Invalid fields");
			} else {
				String regex = "^[A-Za-z ]+";
				if (user.getPassword().equals(user.getReEnterPassword()) && user.getUserName().matches(regex)) {
					return registrationRepository.save(user);
				} else
					throw new NotAbleToUpdateException("Invalid fields");
			}
		} else {
			Logger.error("The entered id is not exist");
			throw new NotAbleToUpdateException("No Such User Id is present");
		}
	}
}
