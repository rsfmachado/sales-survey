package com.sales.survey.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sales.survey.controller.form.SalesSurveyViewForm;
import com.sales.survey.service.SalesSurveyService;
import com.sales.survey.transport.SalesSurveyData;

@Controller
public class SalesSurveyController {

	private static final Logger logger = LoggerFactory.getLogger(SalesSurveyController.class);
	
	@Autowired
	private SalesSurveyService salesSurveyService;
	
	@RequestMapping(value = "/")
	public String homePage() {
		return "index";
	}
	
	@GetMapping(value = "/uploadOneFile")
	public String uploadOneFileHandler(Model model) {
		logger.debug("[SALES_SURVEY]: uploadOneFileHandler(model).");
		SalesSurveyViewForm salesSurveyViewForm = new SalesSurveyViewForm();
		model.addAttribute("salesSurveyViewForm", salesSurveyViewForm);
	 
		return "uploadOneFile";
	}
	
	@PostMapping(value = "/uploadOneFile")
	public String uploadOneFileHandlerPOST(HttpServletRequest request, Model model, @ModelAttribute("salesSurveyViewForm") SalesSurveyViewForm salesSurveyViewForm) {
		logger.debug("[SALES_SURVEY]: uploadOneFileHandlerPOST(request, model, salesSurveyViewForm).");
		return this.doUpload(request, model, salesSurveyViewForm);
	}	
	
	@GetMapping(value = "/uploadMultiFile")
	public String uploadMultiFileHandler(Model model) {
		logger.debug("[SALES_SURVEY]: uploadMultiFileHandler(model).");
		SalesSurveyViewForm salesSurveyViewForm = new SalesSurveyViewForm();
		model.addAttribute("salesSurveyViewForm", salesSurveyViewForm);
	 
		return "uploadMultiFile";
	}
	 
	@PostMapping(value = "/uploadMultiFile")
	public String uploadMultiFileHandlerPOST(HttpServletRequest request, Model model, @ModelAttribute("salesSurveyViewForm") SalesSurveyViewForm salesSurveyViewForm) {
		logger.debug("[SALES_SURVEY]: uploadMultiFileHandlerPOST(request, model, salesSurveyViewForm).");
		return this.doUpload(request, model, salesSurveyViewForm);
	}
	
	private String doUpload(HttpServletRequest request, Model model, SalesSurveyViewForm salesSurveyViewForm) {
		logger.debug("[SALES_SURVEY]: doUpload(request, model, salesSurveyViewForm).");
		 
		String description = salesSurveyViewForm.getDescription();
		logger.debug("[SALES_SURVEY]: doUpload(request, model, salesSurveyViewForm). Description: " + description);
		 
		// Root Directory.
		String uploadRootPath = request.getServletContext().getRealPath("upload");
		logger.debug("[SALES_SURVEY]: doUpload(request, model, salesSurveyViewForm). uploadRootPath: " + uploadRootPath);
		 
		File uploadRootDir = new File(uploadRootPath);
		// Create directory if it not exists.
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		
		SalesSurveyData surveyData = salesSurveyService.survey(salesSurveyViewForm.getFileDatas(), uploadRootDir.getAbsolutePath());
		
		model.addAttribute("description", description);
		model.addAttribute("uploadedFiles", surveyData.getUploadedFiles());
		model.addAttribute("failedFiles", surveyData.getFailedFiles());

		return "uploadResult";
	}
	
}