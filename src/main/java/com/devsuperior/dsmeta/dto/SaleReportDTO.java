package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleReportDTO {

	private Long id;
	private LocalDate date;
	private Double amount;
	private String name;
	
	public SaleReportDTO(Long id, LocalDate date, Double amount, String name) {
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.name = name;
	}
	
	public SaleReportDTO(Sale entity) {
		id = entity.getId();
		date = entity.getDate();
		amount = entity.getAmount();
		name = entity.getSeller().getName();
	}

	public Long getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}

	public Double getAmount() {
		return amount;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SaleReportDTO [id=");
		builder.append(id);
		builder.append(", date=");
		builder.append(date);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
