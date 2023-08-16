package com.app.service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.EmployeeDao;
import com.app.dto.ApiResponse;
import com.app.entities.Employee;

import lombok.val;
@Service
@Transactional
public class ImageHandlingServiceImp implements ImageHandlingService  {

	// dep:emp dao i/f
			@Autowired
			private EmployeeDao empdao;
	 
			//to inject the value of property : "upload .location", from app property file
			@ Value("${upload.location}")
			private String folderLocation;
			
			@PostConstruct
			public void init() {
				System.out.println("in init");
				File folder=new File(folderLocation);
				if(folder.exists()) {
					System.out.println("foolder already exidst");
				}else {
					folder.mkdir();
					System.out.println("folder created");
				}
	
		
			
			}

	
	@Override
	public ApiResponse uploadImage(Long empid, MultipartFile file) throws IOException {
	   		 Employee emp=empdao.findById(empid).orElseThrow(()-> new ResourceNotFoundException("invaild emp id"));
	   		 String path= folderLocation.concat(file.getOriginalFilename());
	   		 System.out.println("path"+path);
	   		 FileUtils.writeByteArrayToFile(new File(path),file.getBytes());
	   		 emp.setImagePath(path);
	   		 
	   		
		return new ApiResponse("file uploaded and store on server");
	}

}
