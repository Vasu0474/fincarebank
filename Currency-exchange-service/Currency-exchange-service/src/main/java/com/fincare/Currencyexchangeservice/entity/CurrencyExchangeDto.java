package com.fincare.Currencyexchangeservice.entity;

public class CurrencyExchangeDto {
	
	private String from;
	private String to;
	private double conversion_multiple;
	

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
	
	

}
