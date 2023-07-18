package com.circleX.vehicle.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.circleX.vehicle.model.Vehicle;

@Repository
public interface VehicleDao extends JpaRepository<Vehicle, Long> {

	@Query("SELECT vehicle from Vehicle vehicle where (:year is null or vehicle.year=:year) and"
			+ " (:make is null or vehicle.make=:make) and (:model is null or vehicle.model=:model)")
	List<Vehicle> findByYearAndMakeAndModel(Integer year, String make, String model);

}
