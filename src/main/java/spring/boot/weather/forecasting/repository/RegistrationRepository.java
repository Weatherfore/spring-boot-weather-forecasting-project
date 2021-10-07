package spring.boot.weather.forecasting.repository;

//import spring.boot.weather.forecasting.model.Administration;
import spring.boot.weather.forecasting.model.Registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

	
}
