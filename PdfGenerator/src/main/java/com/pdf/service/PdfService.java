package com.pdf.service;

import java.io.ByteArrayInputStream;

import com.pdf.exception.InvoiceException;
import com.pdf.model.InvoiceData;

public interface PdfService {
	
	/**
	 * This method is responsible for creating Pdf document, and returns ByteArrayInputStream
	 * 
	 * @param invoiceDTO
	 * 
	 * @return ByteArrayInputStream
	 */
	public ByteArrayInputStream dynamicPdfGenerator(InvoiceData invoiceDTO);
	
	
	
	
	
	/**
	 * This method is responsible for storing the data to DB.
	 * 
	 * @param InvoiceData (Don't have any id's)
	 * 
	 * @return InvoiceData (It contains id's)
	 */
	public InvoiceData saveInvoiceData(InvoiceData data);
	
	
	
	
	/**
	 * This method is used for getting invoiceData using InvoiceId from DB.
	 * 
	 * @param Integer id
	 * 
	 * @return InvoiceData
	 * 
	 * @throws InvoiceException (If there is no data present with given Id).
	 */
	public InvoiceData findInoiDataById(Integer id) throws InvoiceException;
}
