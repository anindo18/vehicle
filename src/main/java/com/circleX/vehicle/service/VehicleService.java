package com.circleX.vehicle.service;

import java.util.List;

import javax.validation.Valid;

import com.circleX.vehicle.exception.VechiclesException;
import com.circleX.vehicle.model.Vehicle;
import com.circleX.vehicle.request.Test;
import com.circleX.vehicle.request.VehicleRequest;

public interface VehicleService {

	public Vehicle createVehicles(VehicleRequest vehicleRequestDTO);

	public Vehicle updateVehicles(Long id, VehicleRequest vehicleRequestDTO) throws VechiclesException;

	public String deleteVehicles(Long id) throws VechiclesException;

	public Vehicle getVehicles(Long id) throws VechiclesException;

	public List<Vehicle> getAllVehicle(Integer year, String make, String model);

	public String uploadFile(Test test);

}
