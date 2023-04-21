package com.pdf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdf.model.Item;

public interface ItemRepo extends JpaRepository<Item, Integer>{

}
