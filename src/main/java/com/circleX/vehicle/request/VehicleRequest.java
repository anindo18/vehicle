package com.circleX.vehicle.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {

	@Min(1950)
	@Max(2050)
	private int year;

	@NotEmpty(message = "Make can not be null or empty")
	private String make;

	@NotEmpty(message = "Model can not be null or empty")
	private String model;

}
