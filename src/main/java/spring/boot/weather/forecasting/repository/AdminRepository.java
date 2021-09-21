package spring.boot.weather.forecasting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.boot.weather.forecasting.model.Administration;

@Repository
public interface AdminRepository extends JpaRepository<Administration, Long>{

		
}
