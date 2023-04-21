package com.pdf.controller;

import java.io.ByteArrayInputStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdf.exception.InvoiceException;
import com.pdf.model.InvoiceData;
import com.pdf.service.PdfService;


@RestController
@RequestMapping("/pdf")
public class GeneratePdfController {

	@Autowired
	private PdfService pdfService;
	
	
	@GetMapping(value = "/generate/{InvoiceId}")
	public ResponseEntity<InputStreamResource> generatePdfDynamically(@PathVariable Integer InvoiceId) throws InvoiceException{
		
		InvoiceData invoiceData = pdfService.findInoiDataById(InvoiceId);
		
		ByteArrayInputStream document = pdfService.dynamicPdfGenerator(invoiceData);
		
		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=myInvoice.pdf");
		return ResponseEntity
				.ok()
				.headers(httpHeaders)
				.contentType(MediaType.APPLICATION_PDF)
				
				.body(new InputStreamResource(document));
	}
	
	@PostMapping(value = "/storeData")
	public ResponseEntity<String> uploadInvoiceDataToDB(@RequestBody @Valid InvoiceData dto){
		
		InvoiceData saveInvoiceData = pdfService.saveInvoiceData(dto);
		
		String res = "Use This InvoiceId for downloading file "+saveInvoiceData.getInvoiceNo();
		
		return new ResponseEntity<>(res,HttpStatus.OK);
		
	}
}
