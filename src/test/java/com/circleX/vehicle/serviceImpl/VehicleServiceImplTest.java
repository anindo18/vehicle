package com.circleX.vehicle.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.circleX.vehicle.dao.VehicleDao;
import com.circleX.vehicle.exception.VechiclesException;
import com.circleX.vehicle.model.Vehicle;
import com.circleX.vehicle.request.VehicleRequest;

@RunWith(SpringJUnit4ClassRunner.class)
public class VehicleServiceImplTest {

	@InjectMocks
	private VehicleServiceImpl vehicleServiceImpl;

	@Mock
	private VehicleDao vehicleDao;

	@Mock
	private ModelMapper modelMapper;

	private final static String MAKE = "Make";
	private final static String MODEL = "Model";
	private final static Integer YEAR = 1950;
	private final static Long ID = 1L;

	@Test
	public void updateVehicles_Success() throws VechiclesException {
		Mockito.when(vehicleDao.findById(ID)).thenReturn(Optional.of(vehicle()));
		Mockito.when(vehicleDao.save(vehicle())).thenReturn(vehicle());
		Vehicle vehicleResponse = vehicleServiceImpl.updateVehicles(ID, vehicleRequest());
		Assert.assertNotNull(vehicleResponse);
		assertEquals(ID, vehicleResponse.getId());
		assertEquals(YEAR, vehicleResponse.getYear());
		Assert.assertEquals(vehicleResponse.getMake(), MAKE);
		Assert.assertEquals(vehicleResponse.getModel(), MODEL);
	}

	@Test(expected = VechiclesException.class)
	public void updateVehicles_VehicleNotFoundException() throws VechiclesException {
		Mockito.when(vehicleDao.findById(ID)).thenReturn(Optional.empty());
		vehicleServiceImpl.updateVehicles(ID, vehicleRequest());
	}

	@Test
	public void getVehicles_Success() throws VechiclesException {
		Mockito.when(vehicleDao.findById(ID)).thenReturn(Optional.of(vehicle()));
		Vehicle vehicleResponse = vehicleServiceImpl.getVehicles(ID);
		Assert.assertNotNull(vehicleResponse);
		assertEquals(ID, vehicleResponse.getId());
		assertEquals(YEAR, vehicleResponse.getYear());
		Assert.assertEquals(vehicleResponse.getMake(), MAKE);
		Assert.assertEquals(vehicleResponse.getModel(), MODEL);
	}

	@Test(expected = VechiclesException.class)
	public void getVehicles_VehicleNotFoundException() throws VechiclesException {
		Mockito.when(vehicleDao.findById(ID)).thenReturn(Optional.empty());
		vehicleServiceImpl.getVehicles(ID);
	}

	@Test
	public void getVehicleList_Success() throws VechiclesException {
		List<Vehicle> vehicleList = new ArrayList();
		vehicleList.add(vehicle());
		Mockito.when(vehicleDao.findByYearAndMakeAndModel(YEAR, MAKE, MODEL)).thenReturn(vehicleList);
		List<Vehicle> vehicleResponse = vehicleServiceImpl.getAllVehicle(YEAR, MAKE, MODEL);
		Assert.assertNotNull(vehicleResponse);
		Assert.assertEquals(vehicleResponse.size(), 1);
		assertEquals(vehicleResponse.get(0).getId(), ID);
		assertEquals(vehicleResponse.get(0).getYear(), YEAR);
		Assert.assertEquals(vehicleResponse.get(0).getMake(), MAKE);
		Assert.assertEquals(vehicleResponse.get(0).getModel(), MODEL);

	}

	@Test
	public void getVehicleList_SuccessEmptyVehicleList() throws VechiclesException {
		List<Vehicle> vehicleList = new ArrayList();
		Mockito.when(vehicleDao.findByYearAndMakeAndModel(YEAR, MAKE, MODEL)).thenReturn(vehicleList);
		List<Vehicle> vehicleResponse = vehicleServiceImpl.getAllVehicle(YEAR, MAKE, MODEL);
		Assert.assertNotNull(vehicleResponse);
		Assert.assertEquals(vehicleResponse.size(), 0);
	}

	@Test
	public void deleteVehicles_Success() throws VechiclesException {
		Mockito.when(vehicleDao.findById(ID)).thenReturn(Optional.of(vehicle()));
		String deleteResponse = vehicleServiceImpl.deleteVehicles(ID);
		Assert.assertNotNull(deleteResponse);
		Assert.assertEquals(deleteResponse, "Deleted Successfully.");
	}

	@Test(expected = VechiclesException.class)
	public void deleteVehicles_VehicleNotFoundException() throws VechiclesException {
		Mockito.when(vehicleDao.findById(ID)).thenReturn(Optional.empty());
		vehicleServiceImpl.deleteVehicles(ID);
	}

	private Vehicle vehicle() {
		return Vehicle.builder().id(ID).make(MAKE).model(MODEL).year(YEAR).build();
	}

	private VehicleRequest vehicleRequest() {
		return VehicleRequest.builder().make(MAKE).model(MODEL).year(YEAR).build();
	}

}
