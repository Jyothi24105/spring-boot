package com.rest.demo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author r3demo
 * Swagger Configuration for the Project.
 * The Swagger UI can be accessed at http://<host:port>/swagger-ui.html
 *
 */

@Configuration
@EnableSwagger2
public class Swagger {

	public static final Contact DEFAULT_CONTACT = new Contact("r3java", "https://github.com/r3demo", "jyothi2410@gmail.com");
	
	public static final ApiInfo DEFAULT_API_INFO= new ApiInfo("Api Documentation", "Api Documentation", "1.0", "urn:tos",
	          DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	
	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = 
			new HashSet<String>(Arrays.asList("application/json","applictaion/xml"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES)
				.apiInfo(DEFAULT_API_INFO)
				.select().apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.build();
	}
}
