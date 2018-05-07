package com.michal.network.config;

import com.michal.network.service.EntityH2Repository;
import com.michal.network.service.EntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by mmeyers on 5/5/18.
 */

@Configuration
@EnableWebMvc
public class SocialNetworkConfig {

    @Bean
    public EntityRepository entityRepository() {return new EntityH2Repository(); }
}
