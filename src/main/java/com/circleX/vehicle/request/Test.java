package com.circleX.vehicle.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Test {

	private MultipartFile file;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	private FileType fileType;

}
