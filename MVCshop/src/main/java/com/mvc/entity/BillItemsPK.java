package com.mvc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillItemsPK  implements Serializable {
	
    private static final long serialVersionUID = 1L;

	    @Column(name = "product_Id")
	    private int productId;

	    @Column(name = "bill_Id")
	    private int billId;
}
