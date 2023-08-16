package com.app;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Day15LabApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day15LabApplication.class, args);
	}

	// Configure ModelMapper bean in Spring boot application class(since this class
	// is
	// implicitly annotated with @Configuration)
	// Meaning -You can add @Bean methods ONLY in such config classes
	@Bean
	public ModelMapper modelMapper() {  //must be public //any method name
		ModelMapper mapper = new ModelMapper();
		//Strict mode => While mapping , src proparty must match with names and  data types with destination  type
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper;
	}

}
