package com.pdf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdf.model.InvoiceData;

public interface InvoiceDataRepo extends JpaRepository<InvoiceData, Integer>{

}
