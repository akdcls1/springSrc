package com.oracle.oBootMybatis01.contoller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	// upLoadForm 시작화면
	@RequestMapping(value="upLoadFormStart")
	public String upLoadFormStart(Model model) {
		System.out.println("upLoadFormStart start...");
		return "upLoadFormStart";	
	}
	
	@RequestMapping(value="uploadForm", method=RequestMethod.GET)
	public void uploadForm() {
		System.out.println("uploadForm GET Start");
		System.out.println();
	}
	
	@RequestMapping(value="uploadForm", method=RequestMethod.POST)
	public String uploadForm(HttpServletRequest request, MultipartFile file1, MultipartFile file2,Model model) throws Exception{
		String uploadPath=request.getSession().getServletContext().getRealPath("/upload/");
		
		System.out.println("uploadForm POST Start");
		logger.info("originalName: "+file1.getOriginalFilename());
		logger.info("size: "+file1.getSize());
		logger.info("contentType: "+file1.getContentType());
		logger.info("uploadPath: "+uploadPath);
		String savedName1 = uploadFile(file1.getOriginalFilename(), file1.getBytes(), uploadPath);
		String savedName2 = uploadFile(file2.getOriginalFilename(), file2.getBytes(), uploadPath);
		
		logger.info("savedName: "+savedName1);
		model.addAttribute("savedName1", savedName1);
		return "upLoadFormStart";
//		return "uploadResult";
	}

	private String uploadFile(String originalName, byte[] fileData, String uploadPath) throws Exception {
		UUID uid = UUID.randomUUID();
//		requestPath = requestPath+"/resources/image";
		System.out.println("uploadPath->"+uploadPath);
		// Directory 생성
		File fileDirectory = new File(uploadPath);
		if(!fileDirectory.exists()) {
			fileDirectory.mkdirs();
			System.out.println("업로드용 폴더 생성 : "+uploadPath);
		}
		
		String savedName = uid.toString()+"_"+originalName;
		logger.info("savedName: "+savedName);
		File target = new File(uploadPath, savedName);
//		File target = new File(requestPath, savedName);
		FileCopyUtils.copy(fileData, target);	// org.springframework.util.FileCopyUtils
		// Service --> DAO 연결
		return savedName;
	}
	
	@RequestMapping(value="uploadFileDelete", method=RequestMethod.GET)
	public String uploadFileDelete(HttpServletRequest request, Model model)  throws Exception{
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		String deleteFile = uploadPath + "60b9cc0b-731f-4719-a5ea-cc8d970943bc_034ec2de440cd025.png";
		logger.info("deleteFile : "+deleteFile);
		System.out.println("uploadFileDelete POST Start");
		int delResult = upFileDelete(deleteFile);
		logger.info("deleteFile result->"+delResult);
		model.addAttribute("deleteFile", deleteFile);
		if(delResult == 1) 
			model.addAttribute("delResult", "1");
		else 
			model.addAttribute("delResult", "0");
		return "uploadResult";
	}

	private int upFileDelete(String deleteFileName) {
		int result = 0;
		logger.info("upFileDelete result->"+deleteFileName);
		File file = new File(deleteFileName);
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("파일 삭제 성공");
				result = 1;
			}else {
				System.out.println("파일 삭제 실패");
				result = 0;
			}
		}
		else {
			System.out.println("파일이 존재하지 않습니다.");
			result = -1;
		}
		
		return result;
	}
	
	
}
