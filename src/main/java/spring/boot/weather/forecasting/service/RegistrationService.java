package spring.boot.weather.forecasting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logging.GlobalResources;
import spring.boot.weather.forecasting.exception.InvalidFieldException;
import spring.boot.weather.forecasting.exception.NoSuchRegistrationException;
import spring.boot.weather.forecasting.model.Registration;
import spring.boot.weather.forecasting.repository.RegistrationRepository;

import javax.transaction.Transactional;

import org.slf4j.Logger;

@Service 
public class RegistrationService {

	private Logger Logger = GlobalResources.getLogger(RegistrationService.class);

	@Autowired
	RegistrationRepository registrationRepository;

	
	//User Registration
	@Transactional
	public boolean addUser(Registration newUser) throws InvalidFieldException {
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

	public Registration updatingUser(Registration user) throws NoSuchRegistrationException {
		if (registrationRepository.existsById(user.getRid()))
			return registrationRepository.save(user);
		else {
			Logger.error("The entered id is not exist");
			throw new NoSuchRegistrationException("No Such User Id is present");
		}

	}

	
}
