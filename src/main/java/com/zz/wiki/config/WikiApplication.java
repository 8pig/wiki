package com.zz.wiki.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@ComponentScan({"com.zz.wiki"})
@SpringBootApplication
public class WikiApplication {
	private static final Logger LOG = LoggerFactory.getLogger(WikiApplication.class);
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WikiApplication.class);
		Environment env = app.run(args).getEnvironment();
		String port = env.getProperty("server.port");
		LOG.info("success:" + port);


	}

}
