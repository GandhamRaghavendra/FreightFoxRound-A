package com.pdf.service;

import java.io.ByteArrayInputStream;

import org.springframework.stereotype.Service;

import com.pdf.model.InvoiceDTO;

@Service
public class PdfServiceImpl implements PdfService {

	@Override
	public ByteArrayInputStream dynamicPdfGenerator(InvoiceDTO invoiceDTO) {
		return null;
	}

}
