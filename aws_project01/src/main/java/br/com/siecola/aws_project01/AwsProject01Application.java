package br.com.siecola.aws_project01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class AwsProject01Application {
	private static final Logger log = LoggerFactory.getLogger(AwsProject01Application.class);

	public static void main(String[] args) {
		SpringApplication.run(AwsProject01Application.class, args);

		log.info("Starting Project01 example");
	}

}
