package com.fincare.currencyconversionservice.controller;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fincare.currencyconversionservice.entity.CurrencyConversion;
import com.fincare.currencyconversionservice.entity.CurrencyExchangeProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
public class CurrencyConversionController {
	
	@Autowired
	CurrencyExchangeProxy currencyExchangeProxy;
	
	Logger log=LoggerFactory.getLogger(CurrencyConversionController.class);
	
	RestTemplate restTemplate=new RestTemplate();
	
	@RequestMapping(value = "/currency-conversion/from/{from}/to/{to}/quantity/{quantity}",method = RequestMethod.GET)
	public CurrencyConversion getData(@PathVariable("from") String from,@PathVariable("to") String to,@PathVariable("quantity") int quantity)
	{
		log.info("Currency-conversion/get data");
		Map<String, String> uriVariables=new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversion> e=restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class,uriVariables);
		CurrencyConversion currencyConversion=e.getBody();
		CurrencyConversion currencyConver=new CurrencyConversion(currencyConversion.getId(),from,to,
				currencyConversion.getConversion_multiple(),quantity,quantity*currencyConversion.getConversion_multiple(),"8100");
		//EntityModel<CurrencyConversion> model=EntityModel.of(currencyConver);
		
		return currencyConver;
	}
	
	@RequestMapping(value = "/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}",method = RequestMethod.GET)
	public EntityModel<CurrencyConversion> getDataFromFeign(@PathVariable("from") String from,@PathVariable("to") String to,@PathVariable("quantity") int quantity)
	{
		
		log.info("inside getDataFromFeign method");
		CurrencyConversion currencyConversion=currencyExchangeProxy.getData(from, to);
		System.out.println("Currency-conversion/feign method");
		CurrencyConversion currencyConver=new CurrencyConversion(currencyConversion.getId(),from,to,
				currencyConversion.getConversion_multiple(),quantity,quantity*currencyConversion.getConversion_multiple(),"8100");
		EntityModel<CurrencyConversion> model=EntityModel.of(currencyConver);
		
		return model;
	}


}
