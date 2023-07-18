package com.circleX.vehicle.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.circleX.vehicle.exception.VechiclesException;
import com.circleX.vehicle.model.Vehicle;
import com.circleX.vehicle.request.Test;
import com.circleX.vehicle.request.VehicleRequest;
import com.circleX.vehicle.service.VehicleService;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

	private final VehicleService vehicleService;

	public VehicleController(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	@PostMapping
	public ResponseEntity<Vehicle> createVehicles(@Valid @RequestBody VehicleRequest vehicleRequestDTO) {
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(vehicleService.createVehicles(vehicleRequestDTO));
	}

	@PutMapping("{id}")
	public ResponseEntity<Vehicle> updateVehicles(Long id, @Valid @RequestBody VehicleRequest vehicleRequestDTO)
			throws VechiclesException {
		return ResponseEntity.status(HttpStatus.OK.value()).body(vehicleService.updateVehicles(id, vehicleRequestDTO));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteVehicles(@PathVariable Long id) throws VechiclesException {
		return ResponseEntity.status(HttpStatus.OK.value()).body(vehicleService.deleteVehicles(id));
	}

	@GetMapping("{id}")
	public ResponseEntity<Vehicle> getVehicle(@PathVariable Long id) throws VechiclesException {
		return ResponseEntity.status(HttpStatus.OK.value()).body(vehicleService.getVehicles(id));
	}

	@GetMapping
	public ResponseEntity<List<Vehicle>> getAllVehicles(@RequestParam(value = "year", required = false) Integer year,
			@RequestParam(value = "make", required = false) String make,
			@RequestParam(value = "model", required = false) String model) {
		return ResponseEntity.status(HttpStatus.OK.value()).body(vehicleService.getAllVehicle(year, make, model));
	}

	@PostMapping(value = "upload-police-document", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<String> testUpload(@ModelAttribute("test") Test test) {
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(vehicleService.uploadFile(test));
	}

}
