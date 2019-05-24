package com.sales.survey.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
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
import com.sales.survey.transport.SalesSurveyResult;

@Controller
public class SalesSurveyController {

	private static final Logger logger = LoggerFactory.getLogger(SalesSurveyController.class);
	
	@Autowired
	private SalesSurveyService salesSurveyService;
	
	@RequestMapping(value = "/")
	public String homePage() {
		return "index";
	}
	
	@GetMapping("/uploadOneFile")
	public String uploadOneFileHandler(Model model) {
		logger.debug("[SALES_SURVEY]: uploadOneFileHandler(model).");
		
		SalesSurveyViewForm salesSurveyViewForm = new SalesSurveyViewForm();
		model.addAttribute("salesSurveyViewForm", salesSurveyViewForm);
	 
		return "uploadOneFile";
	}
	
	@PostMapping("/uploadOneFile")
	public String uploadOneFileHandlerPOST(HttpServletRequest request, Model model, @ModelAttribute("salesSurveyViewForm") SalesSurveyViewForm salesSurveyViewForm) {
		logger.debug("[SALES_SURVEY]: uploadOneFileHandlerPOST(request, model, salesSurveyViewForm).");
		return this.doUpload(request, model, salesSurveyViewForm);
	}	
	
	@GetMapping("/uploadMultiFile")
	public String uploadMultiFileHandler(Model model) {
		logger.debug("[SALES_SURVEY]: uploadMultiFileHandler(model).");
		
		SalesSurveyViewForm salesSurveyViewForm = new SalesSurveyViewForm();
		model.addAttribute("salesSurveyViewForm", salesSurveyViewForm);
	 
		return "uploadMultiFile";
	}
	 
	@PostMapping("/uploadMultiFile")
	public String uploadMultiFileHandlerPOST(HttpServletRequest request, Model model, @ModelAttribute("salesSurveyViewForm") SalesSurveyViewForm salesSurveyViewForm) {
		logger.debug("[SALES_SURVEY]: uploadMultiFileHandlerPOST(request, model, salesSurveyViewForm).");
		return this.doUpload(request, model, salesSurveyViewForm);
	}
	
	private String doUpload(HttpServletRequest request, Model model, SalesSurveyViewForm salesSurveyViewForm) {
		logger.debug("[SALES_SURVEY]: doUpload(request, model, salesSurveyViewForm).");
		 
		String uploadRootPath = request.getServletContext().getRealPath("upload");
		logger.debug("[SALES_SURVEY]: doUpload(request, model, salesSurveyViewForm). uploadRootPath: " + uploadRootPath);
		 
		File uploadRootDir = new File(uploadRootPath);
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		
		SalesSurveyResult surveyResult = salesSurveyService.survey(salesSurveyViewForm.getFileDatas(), uploadRootDir.getAbsolutePath());
		
		model.addAttribute("salesSurveyResult", surveyResult);

		return "uploadResult";
	}
	
	@PostMapping(value = "/download")
	public void download(HttpServletResponse response, Model model, @ModelAttribute("salesSurveyResult") SalesSurveyResult salesSurveyResult) {
		logger.debug("[SALES_SURVEY]: download(response, model, salesSurveyResult):");
		
		response.addHeader("Content-Disposition", "attachment; filename=\"sales_survey_result.done.dat\"");
		response.setContentType("text/plain");
		response.setStatus(HttpServletResponse.SC_OK);
		
		try {
			FileInputStream fileInputStream = new FileInputStream(createFile(salesSurveyResult));

			IOUtils.copy(fileInputStream, response.getOutputStream());

			fileInputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private File createFile(SalesSurveyResult salesSurveyResult) {
		
		PrintWriter writer;
		try {
			writer = new PrintWriter("sales_survey_result.done.dat", "UTF-8");
			writer.println("Total de Clientes: " + salesSurveyResult.getNumberOfClients());
			writer.println("Total de Vendedores: " + salesSurveyResult.getNumberOfSellers());
			writer.println("Id da Melhor Venda: " + salesSurveyResult.getBestSaleID());
			writer.println("Pior vendedor: " + salesSurveyResult.getWorstSellerName());
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new File("sales_survey_result.done.dat");
	}
	
}