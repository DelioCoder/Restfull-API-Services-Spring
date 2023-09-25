package com.programmingGeek02.rest.webservices.restfulwebservices.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.programmingGeek02.rest.webservices.restfulwebservices.filtering.SomeBean;

@RestController
public class FilteringController
{
	@GetMapping("filtering")
	public MappingJacksonValue filtering()
	{
		
		SomeBean someBean = new SomeBean("Value1", "Value2", "Value3");
		
		MappingJacksonValue mappingJacksonValue = filterBeans(someBean, "field1", "field3");
		
		return mappingJacksonValue;
	}
	
	@GetMapping("filtering-list")
	public MappingJacksonValue filteringList()
	{
		
		List<SomeBean> someBeans  = Arrays.asList(new SomeBean("Value1", "Value2", "Value3"), new SomeBean("Value4", "Value5", "Value6"));
		
		MappingJacksonValue mappingJacksonValue = filterBeans(someBeans, "field2", "field3");
		
		return mappingJacksonValue;
	}
	
	private <T> MappingJacksonValue filterBeans(T beans, String... propertyArrays) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(beans);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(propertyArrays);
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
   }
	
}
