package com.appsdeveloper.app.ws.mobileappws.shared;

import com.appsdeveloper.app.ws.mobileappws.context.SpringApplicationContext;
import com.appsdeveloper.app.ws.mobileappws.security.AppProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Utils {
    public String getUUID() {
        return String.valueOf(UUID.randomUUID());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringApplicationContext springApplicationContext() {
        return new SpringApplicationContext();
    }
}
