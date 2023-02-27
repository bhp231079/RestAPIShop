package com.fe.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillItemsPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private int productId;

	private int billId;
}
