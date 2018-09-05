package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
@ConfigurationProperties(prefix="taco.orders")
public class HomeController {
	
	private int limit = 100;
	
	@Autowired
	private MeterRegistry reg; 
	
	@GetMapping("/")
	public String home(@RequestParam(value="name",required=false)final String name) {
		System.out.println("limit: " + limit); 
		
		log.info("limit: " + limit);
		
		reg.counter("taco." + name.toLowerCase()).increment();
		
		return "hola";
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	
}
