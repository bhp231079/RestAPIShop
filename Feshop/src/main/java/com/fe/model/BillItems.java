package com.fe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class BillItems {
	
	

	private BillItemsPK id;


	private Product product;
	
	
	
	private Bill bill;

	private int quantity;

	private double sum;
	
	

}
