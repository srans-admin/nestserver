package com.srans.nestserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "expensetype")
public class ExpensesType extends AuditModel {

	/**
	 * @author Manish
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long typeId;

	@Column
	private String expenseType;

	public ExpensesType() {
		super();

	}

	public ExpensesType(Long typeId, String expenseType) {
		super();
		this.typeId = typeId;
		this.expenseType = expenseType;
		
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExpensesType [typeId=");
		builder.append(typeId);
		builder.append(", expenseType=");
		builder.append(expenseType);
		builder.append("]");
		return builder.toString();
	}
	
}
