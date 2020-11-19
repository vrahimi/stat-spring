package com.rd.scm.rest;

import java.net.URI;
import java.time.Year;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rd.scm.rest.exception.UserNotFoundException;

@RestController
public class TestController {

	@Autowired
	private ResourceBundleMessageSource messageSource;

	/* @ResponseBody is not necessary because it is the default */
	@RequestMapping(value = "/ex/foos", method = RequestMethod.GET)
	@ResponseBody
	public String getFoosBySimplePath() {
		return "Get some Foos";
	}

	// Returns a json object
	@RequestMapping(value = "/ex/foosObject", method = RequestMethod.GET)
	public ResponseTransfer getFoosObject() {
		return new ResponseTransfer("Get some Foos");
	}

	// Post with a request body
	/*
	 * curl -i \ -H "Accept: application/json" \ -H "Content-Type:application/json"
	 * \ -X POST --data '{"text": "johnny"}' "https://localhost:8080/.../request"
	 * 
	 */
	@PostMapping("/request")
	public ResponseEntity<Object> postController(@Valid @RequestBody LoginForm loginForm, HttpServletRequest request)
			throws Exception {

		URI location = UriComponentsBuilder.fromUri(new URI(request.getRequestURI())).path("{/id}").buildAndExpand(1)
				.toUri();

		// BodyBuilder accepted();
		// BodyBuilder badRequest();
		// BodyBuilder created(java.net.URI location);
		// HeadersBuilder<?> noContent();
		// HeadersBuilder<?> notFound();
		// BodyBuilder ok();

		return ResponseEntity.created(location).build();
	}

	// http://localhost:8080/api/ex/foosParam?data=one
	// Note that data=one is required here or you get an error
	@RequestMapping(value = "/ex/foosParam", method = RequestMethod.GET)
	public @ResponseBody ResponseTransfer getItem(@RequestParam("data") String itemid) {

		return new ResponseTransfer("get some foos with params: " + itemid);
	}

	// Here if data is left out, you get a string of Optional.empty
	@RequestMapping(value = "/ex/foosParam1", method = RequestMethod.GET)
	public @ResponseBody ResponseTransfer getItemOptional(@RequestParam("data") Optional<String> itemid) {

		return new ResponseTransfer("get some foos with optional params: " + itemid.orElse("not provided"));
	}

	// new syntax to allow data being optional
	@RequestMapping(value = "/ex/foosParam2", method = RequestMethod.GET)
	public @ResponseBody ResponseTransfer getItemOther(
			@RequestParam(value = "data", required = false, defaultValue = "default") String itemid) {

		return new ResponseTransfer("get some foos with required params: " + itemid);
	}

	// version=2 is a required param on the url
	@GetMapping(value = "/ex/foosParam3", params = "version=2")
	public @ResponseBody ResponseTransfer getItemOther1() {

		return new ResponseTransfer("get some foos with required params with version2 ");
	}

	// when /ex/foosParam4 is called, you need to pass in X-API-VERSION=2 in the
	// header
	@GetMapping(value = "/ex/foosParam4", headers = "X-API-VERSION=2")
	public @ResponseBody ResponseTransfer getItemOther2() {

		return new ResponseTransfer("get some foos with required params with version2 in header ");
	}

	// when /ex/foosParam5 is called, you need to pass in
	// Accept=application/rd.app-v1+json
	// in header
	@GetMapping(value = "/ex/foosParam5", produces = "application/rd.app-v1+json")
	public @ResponseBody ResponseTransfer getItemOther3() {

		return new ResponseTransfer("get some foos with required params with mime type application ");
	}

	@GetMapping("/ex/foos/{name}")
	public String postController(@PathVariable String name) {

		return "path variable: " + name;
	}

	@GetMapping("/ex/foosEx/{name}")
	public String postControllerExceptionHandling(@PathVariable String name) {

		if (name.equals("error")) {
			throw new UserNotFoundException("name-" + name);
		}
		return "path variable: " + name;
	}

	@GetMapping("/age")
	public ResponseEntity<String> age(@RequestParam("yearOfBirth") int yearOfBirth) {

		// Of course this is optional
		HttpHeaders headers = new HttpHeaders();
		headers.add("Custom-Header", "foo");

		if (isInFuture(yearOfBirth)) {
			return new ResponseEntity<>("Year of birth cannot be in the future", HttpStatus.BAD_REQUEST);
		}

		// return ResponseEntity.ok("it was ok");
		return new ResponseEntity<>("Your age is " + calculateAge(yearOfBirth), headers, HttpStatus.OK);
	}

	@DeleteMapping("/ex/foosDel")
	public void deleteUser(@PathVariable String name) {

		if (name.equals("error")) {
			throw new UserNotFoundException("name-" + name);
		}
	}

	// The locale comes in from the request header. set the Accept-Language to 'fr'
	// or 'en'
	@GetMapping("/ex/foosInternational")
	public String helloInternational(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {

		return messageSource.getMessage("message.greetings", null, locale);
	}

	@GetMapping("/ex/foosFilter")
	public FilterBean retriveFilter() {

		return new FilterBean("value1", "value2", "value3");
	}

	@GetMapping("/ex/foosFilterDynamic")
	public MappingJacksonValue retriveFilterDynamic() {

		DynamicFilterBean bean = new DynamicFilterBean("value1", "value2", "value3");
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("text1");

		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(bean);
		mapping.setFilters(filters);

		return mapping;
	}

	private int calculateAge(int yearOfBirth) {
		return currentYear() - yearOfBirth;
	}

	private boolean isInFuture(int year) {
		return currentYear() < year;
	}

	private int currentYear() {
		return Year.now().getValue();
	}

	static class LoginForm {

		// In you .m2 repository, look at the jar file for
		// javax\validation\validation-api\2.0.1.Final\validation-api-2.0.1.Final.jar
		// open the jar and look at javax\validation\constraints\ for a list of all
		// available validations
		@Size(min = 2)
		private String text;

		public LoginForm() {
		}

		public LoginForm(String t) {
			this.text = t;
		}

		public String getText() {
			return this.text;
		}

		public void setText(String n) {
			this.text = n;
		}
	}

	static class ResponseTransfer {
		private String text;

		public ResponseTransfer() {
		}

		public ResponseTransfer(String t) {
			this.text = t;
		}

		public String getText() {
			return this.text;
		}

		public void setText(String n) {
			this.text = n;
		}
	}

	// @JsonIgnoreProperties(value={"text", "text1"})
	static class FilterBean {
		private String text;
		private String text1;

		@JsonIgnore
		private String text2;

		public FilterBean() {
		}

		public FilterBean(String t, String t1, String t2) {
			this.text = t;
			this.text1 = t1;
			this.text2 = t2;
		}

		public String getText() {
			return this.text;
		}

		public void setText(String n) {
			this.text = n;
		}

		public String getText1() {
			return this.text1;
		}

		public void setText1(String n) {
			this.text1 = n;
		}

		public String getText2() {
			return this.text2;
		}

		public void setText2(String n) {
			this.text2 = n;
		}
	}

	@JsonFilter("SomeBeanFilter")
	static class DynamicFilterBean {
		private String text;
		private String text1;

		@JsonIgnore
		private String text2;

		public DynamicFilterBean() {
		}

		public DynamicFilterBean(String t, String t1, String t2) {
			this.text = t;
			this.text1 = t1;
			this.text2 = t2;
		}

		public String getText() {
			return this.text;
		}

		public void setText(String n) {
			this.text = n;
		}

		public String getText1() {
			return this.text1;
		}

		public void setText1(String n) {
			this.text1 = n;
		}

		public String getText2() {
			return this.text2;
		}

		public void setText2(String n) {
			this.text2 = n;
		}
	}
}
