package com.srans.nestserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "expenseType")
public class ExpensesCategory extends AuditModel {

	/**
	 * @author Manish
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long typeId;

	@Column
	private String expenseType;
	
	@Column
	private String description;

	public ExpensesCategory() {
		super();
	
	}

	public ExpensesCategory(Long typeId, String expenseType, String description) {
		super();
		this.typeId = typeId;
		this.expenseType = expenseType;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExpensesCategory [typeId=");
		builder.append(typeId);
		builder.append(", expenseType=");
		builder.append(expenseType);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
}

	