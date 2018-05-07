package com.michal.network;

import com.michal.network.config.SocialNetworkConfig;
import com.michal.network.service.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class SocialNetworkApplication {


	EntityRepository repository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		final Class[] sources =
				{
						SocialNetworkConfig.class,
				};

		SpringApplication.run(sources, args);
		//SpringApplication.run(SocialNetworkApplication.class, args);
	}

}
