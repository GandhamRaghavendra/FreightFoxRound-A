package com.pdf.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdf.model.InvoiceDTO;
import com.pdf.service.PdfService;

@RestController
@RequestMapping("/pdf")
public class GeneratePdfController {

	@Autowired
	private PdfService pdfService;
	
	@PostMapping("/generate")
	public ResponseEntity<InputStreamResource> generatePdfDynamically(@RequestBody InvoiceDTO dto){
		
		ByteArrayInputStream document = pdfService.dynamicPdfGenerator(dto);
		
		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add("Content-Disposition", "inline;filename=myInvoice.pdf");

		return ResponseEntity
				.ok()
				.headers(httpHeaders)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(document));
	}
}
