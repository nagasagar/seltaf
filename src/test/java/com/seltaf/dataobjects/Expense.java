package com.seltaf.dataobjects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Expense {
	
	private String expensedetail;

	@XmlElement
	public String getExpensedetail() {
		return expensedetail;
	}

	public void setExpensedetail(String expensedetail) {
		this.expensedetail = expensedetail;
	}

}
