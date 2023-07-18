package com.circleX.vehicle.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.circleX.vehicle.dao.VehicleDao;
import com.circleX.vehicle.exception.VechiclesException;
import com.circleX.vehicle.model.TestModel;
import com.circleX.vehicle.model.Vehicle;
import com.circleX.vehicle.request.Test;
import com.circleX.vehicle.request.VehicleRequest;
import com.circleX.vehicle.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	private final VehicleDao vehicleDao;

	private final ModelMapper modelMapper;

	private static final String VEHICLE_NOT_EXISTS_IN_DB = "Vehicle is not present in DB with id  :";

	public VehicleServiceImpl(VehicleDao vehicleDao, ModelMapper modelMapper) {
		this.vehicleDao = vehicleDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public Vehicle createVehicles(VehicleRequest vehicleRequestDTO) {
		Vehicle vehicles = modelMapper.map(vehicleRequestDTO, Vehicle.class);
		return vehicleDao.save(vehicles);
	}

	@Override
	public Vehicle updateVehicles(Long id, VehicleRequest vehicleRequestDTO) throws VechiclesException {
		Optional<Vehicle> vehicles = vehicleDao.findById(id);
		if (!vehicles.isPresent()) {
			throw new VechiclesException(HttpStatus.NOT_FOUND, VEHICLE_NOT_EXISTS_IN_DB + id);
		}
		Vehicle vehicle = vehicles.get();
		vehicle.setMake(vehicleRequestDTO.getMake());
		vehicle.setModel(vehicleRequestDTO.getModel());
		vehicle.setYear(vehicleRequestDTO.getYear());
		return vehicleDao.save(vehicle);
	}

	@Override
	public String deleteVehicles(Long id) throws VechiclesException {
		Optional<Vehicle> vehicles = vehicleDao.findById(id);
		if (!vehicles.isPresent()) {
			throw new VechiclesException(HttpStatus.NOT_FOUND, VEHICLE_NOT_EXISTS_IN_DB + id);
		}
		vehicleDao.delete(vehicles.get());
		return "Deleted Successfully.";
	}

	@Override
	public Vehicle getVehicles(Long id) throws VechiclesException {
		Optional<Vehicle> vehicles = vehicleDao.findById(id);
		if (!vehicles.isPresent()) {
			throw new VechiclesException(HttpStatus.NOT_FOUND, VEHICLE_NOT_EXISTS_IN_DB + id);
		}
		return vehicles.get();
	}

	@Override
	public List<Vehicle> getAllVehicle(Integer year, String make, String model) {
		List<Vehicle> vehicleList = vehicleDao.findByYearAndMakeAndModel(year, make, model);
		return vehicleList.isEmpty() ? new ArrayList() : vehicleList;
	}

	@Override
	public String uploadFile(Test test) {
		TestModel vehicles = modelMapper.map(test, TestModel.class);
		System.out.println("data :"+vehicles);
		return "ok";
	}
}
