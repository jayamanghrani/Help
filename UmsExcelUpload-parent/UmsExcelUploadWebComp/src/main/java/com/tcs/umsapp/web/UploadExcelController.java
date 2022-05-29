package com.tcs.umsapp.web;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tcs.umsapp.upload.controller.UploadDataController;
import com.tcs.umsapp.upload.vo.cmo.UmsappResponseObject;
import com.tcs.umsapp.web.util.HttpHeaderUtil;



@Component
@RestController
@RequestMapping("/upload")
public class UploadExcelController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UploadExcelController.class);

	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,HttpServletRequest request){
		String firstname=null;
		String userId=null;
		String memberOf = null;
		
		firstname = request.getHeader("firstname");
		userId = request.getHeader("loginID");
		memberOf = request.getHeader("memberOf");
		LOGGER.info("Inside uploadFile");
		
		UmsappResponseObject umsappResponseObject;
		
		if(file.isEmpty()){
			return new ResponseEntity<Object>("please select a file!", HttpStatus.OK);
		}
		try{
			umsappResponseObject  = new UploadDataController().addRoleDetail(file,userId);
			LOGGER.info("userid in webcomp"+userId);
		}catch(Exception ex){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Object>(umsappResponseObject, HttpStatus.OK);
	}
	
}
