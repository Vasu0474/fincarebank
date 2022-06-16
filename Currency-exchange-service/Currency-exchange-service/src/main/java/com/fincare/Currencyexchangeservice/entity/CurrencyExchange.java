package com.fincare.Currencyexchangeservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

@Entity
public class CurrencyExchange {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "exchange_from")
	@NotEmpty(message = "From field cannot be empty")
	private String from;
	@Column(name = "exchange_to")
	@NotEmpty(message = "To field cannot be empty")
	private String to;
	//@Size(min = 1,max = 300,message = "Enter conversion_multiple range between 1 and 300")
	private double conversion_multiple;
	
	private String environment;

	
	public CurrencyExchange() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CurrencyExchange(Long id, String from, String to, double conversion_multiple, String environment) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversion_multiple = conversion_multiple;
		this.environment = environment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	@Override
	public String toString() {
		return "CurrencyExchange [id=" + id + ", from=" + from + ", to=" + to + ", conversion_multiple="
				+ conversion_multiple + ", environment=" + environment + "]";
	}
	
	

}
