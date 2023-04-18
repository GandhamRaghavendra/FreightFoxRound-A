package com.pdf.service;

import java.io.ByteArrayInputStream;
import com.pdf.model.InvoiceDTO;

public interface PdfService {
	
	public ByteArrayInputStream dynamicPdfGenerator(InvoiceDTO invoiceDTO);
}
