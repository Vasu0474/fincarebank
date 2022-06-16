package com.fincare.currencyconversionservice.entity;

public class CurrencyConversion {

	private long id;
	private String from;
	private String to;
	private double conversion_multiple;
	private int quantity;
	private double calculated_amount;
	private String environment;
	
	
	@Override
	public String toString() {
		return "CurrencyConversion [id=" + id + ", from=" + from + ", to=" + to + ", conversion_multiple="
				+ conversion_multiple + ", quantity=" + quantity + ", calculated_amount=" + calculated_amount
				+ ", environment=" + environment + "]";
	}
	public CurrencyConversion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CurrencyConversion(long id, String from, String to, double conversion_multiple, int quantity,
			double calculated_amount, String environment) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversion_multiple = conversion_multiple;
		this.quantity = quantity;
		this.calculated_amount = calculated_amount;
		this.environment = environment;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public double getConversion_multiple() {
		return conversion_multiple;
	}
	public void setConversion_multiple(double conversion_multiple) {
		this.conversion_multiple = conversion_multiple;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getCalculated_amount() {
		return calculated_amount;
	}
	public void setCalculated_amount(double calculated_amount) {
		this.calculated_amount = calculated_amount;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
	
}
