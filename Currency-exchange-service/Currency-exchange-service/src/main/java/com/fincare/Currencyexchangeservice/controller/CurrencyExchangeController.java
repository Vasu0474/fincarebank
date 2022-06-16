package com.fincare.Currencyexchangeservice.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fincare.Currencyexchangeservice.entity.CurrencyExchange;
import com.fincare.Currencyexchangeservice.entity.CurrencyExchangeDto;
import com.fincare.Currencyexchangeservice.exception.CustomException;
import com.fincare.Currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;
	@Autowired
	private Environment environment;
	
	Logger log=LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	
	@PostMapping("/currency-exchange")
	public ResponseEntity<String> saveEntry(@Valid @RequestBody CurrencyExchange currencyExchange)
	{
		String port=environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		currencyExchangeRepository.save(currencyExchange);
		return new ResponseEntity<String>("Entry Saved",HttpStatus.OK);
	}
	
	@GetMapping("/currency-exchange/get-info")
	public List<CurrencyExchangeDto> getInfo()
	{
		log.info("inside getInfo method");
		List<CurrencyExchangeDto> currencyDto=new ArrayList<>();
		List<CurrencyExchange> list=currencyExchangeRepository.findAll();
		//System.out.println(list.toString());
		if(list.isEmpty())
		{
			throw new CustomException("there are no records present");
		}
	
		List<CurrencyExchangeDto> list1=list.stream()
				.map((CurrencyExchange e) -> new ModelMapper()
						.map(e,CurrencyExchangeDto.class ))
				.collect(Collectors.toList());
		return list1;
	}
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public EntityModel<CurrencyExchange> getData(@PathVariable("from") String from, @PathVariable("to") String to)
	{
		log.info("Currency-exchange/inside getData method");
		CurrencyExchange currencyExchange=currencyExchangeRepository.findByFromAndTo(from, to);
		if(currencyExchange==null)
		{
			throw new CustomException("Unable to find the data from : "+from+" to "+to);
		}
		String port=environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		EntityModel<CurrencyExchange> model=EntityModel.of(currencyExchange);
		WebMvcLinkBuilder linkToCurrency= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getInfo());
		model.add(linkToCurrency.withRel("to-see-all-currency"));
		return model;
	}

}
