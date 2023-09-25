package com.programmingGeek02.rest.webservices.restfulwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programmingGeek02.rest.webservices.restfulwebservices.model.Name;
import com.programmingGeek02.rest.webservices.restfulwebservices.model.PersonV1;
import com.programmingGeek02.rest.webservices.restfulwebservices.model.PersonV2;

@RestController
public class VersioningPersonController
{
	// Twitter
	
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson()
	{
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson()
	{
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	// Amazon
	
	@GetMapping(path="/person", params = "version=1")
	public PersonV1 getFirstVersionOfPersonRequestParameter()
	{
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path="/person", params = "version=2")
	public PersonV2 getSecondVersionOfPersonRequestParameter()
	{
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	// Microsoft
	
	@GetMapping(path="/person", headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonRequestHeader()
	{
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path="/person", headers = "X-AIP-VERSION=2")
	public PersonV2 getSecondVersionOfPersonRequestHeader()
	{
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	// GitHub
	
	@GetMapping(path="/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeader()
	{
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path="/person/accept",  produces = "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeader()
	{
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
}
