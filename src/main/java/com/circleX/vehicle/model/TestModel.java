package com.circleX.vehicle.model;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.circleX.vehicle.request.FileType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TestModel {
	
	private MultipartFile file;

	private LocalDate startDate;

	private FileType fileType;

}
