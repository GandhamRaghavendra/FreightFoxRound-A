package com.pdf.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.pdf.model.InvoiceDTO;
import com.pdf.model.Item;

@Service
public class PdfServiceImpl implements PdfService {

	@Override
	public ByteArrayInputStream dynamicPdfGenerator(InvoiceDTO invoiceDTO) {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		Document document = new Document();

		PdfWriter.getInstance(document, out);
		
		document.open();
		
		// Add seller and buyer sections
        PdfPTable sectionsTable = new PdfPTable(2);
        
        // Setting width of sectionTable.
        sectionsTable.setWidths(new float[] { 5, 5 });
        

        // appending data of seller to cell.
        PdfPCell cell = new PdfPCell(new Paragraph("Seller:\n"
                                                              +invoiceDTO.getSeller()+"\n"
        		                                              +invoiceDTO.getSellerAddress()+"\nGSTIN: "
                                                              +invoiceDTO.getSellerGstin()));
        
        // setting Boarder to seller cell.
        cell.setBorder(Rectangle.BOX);
        
        // setting padding to seller cell.
        cell.setPadding(15f);
        
        // adding cell to sectionsTable.
        sectionsTable.addCell(cell);
        
        
        // appending data of Buyer to cell.
        cell = new PdfPCell(new Paragraph("Buyer:\n"
									                +invoiceDTO.getBuyer()+"\n"
									                +invoiceDTO.getBuyerAddress()+"\nGSTIN: "
									                +invoiceDTO.getBuyerGstin()));
        
        // setting Boarder to buyer cell.
        cell.setBorder(Rectangle.BOX);
        
        // setting padding to buyer cell.
        cell.setPadding(15f);
        
        // adding cell to sectionsTable.
        sectionsTable.addCell(cell);
        
        
        // Create the table
        PdfPTable table = new PdfPTable(4);
        table.setWidths(new float[] { 3, 2, 2, 2 });

        // Add header row
        cell = new PdfPCell(new Paragraph("Item"));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setPadding(7f);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Quantity"));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setPadding(7f);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Rate"));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setPadding(7f);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Amount"));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setPadding(7f);
        table.addCell(cell);
        
        // getting List of item in Dto.
        List<Item> itemslist = invoiceDTO.getItems();
        
        
        // Loop on ItemList start..
        for(Item item : itemslist) {
    
        	// Adding data in item row
        	cell = new PdfPCell(new Paragraph(item.getName()));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setPadding(5f);
            table.addCell(cell);
            
            // Adding data in quantity row
            cell = new PdfPCell(new Paragraph(item.getQuantity()));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setPadding(5f);
            table.addCell(cell);
            
            // Adding data in rate row
            cell = new PdfPCell(new Paragraph(""+item.getRate()));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setPadding(5f);
            table.addCell(cell);
            
            // Adding data in amount row
            cell = new PdfPCell(new Paragraph(""+item.getAmount()));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setPadding(5f);
            table.addCell(cell);
        }
        // Loop on ItemList end..
        
           
        // Add everything to the document
        document.add(sectionsTable);
        document.add(table);

        // Close the document
        document.close();
        
        // Converting "ByteArrayOutputStream" to "ByteArrayInputStream" and returning.
        return new ByteArrayInputStream(out.toByteArray());
	}

}
