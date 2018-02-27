package com.rest.demo.resource;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rest.demo.model.FilterBean;

/**
 * @author r3demo
 * A Demo Spring Boot Rest Controller to demonstrate 
 * dynamic filtering of Model fields
 *
 */

@RestController
class FilteringController {
	
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		
		FilterBean someBean = new FilterBean("value1","value2" ,"value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
				filterOutAllExcept("field1","field2");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
		
		MappingJacksonValue mapping  = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		
		return mapping;
	}
}
