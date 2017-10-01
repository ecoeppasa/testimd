package com.elko.imd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is main class of this application.
 * when application is run spring boot will call this class first, then all the spring configuration automatically initialize
 * by spring boot annotation (<code>@SpringBootApplication</code>)
 * 
 * @author elko
 * 
 * @version 1.0
 * @since 2017-09-25
 * 
 * @see <a href="https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/autoconfigure/SpringBootApplication.html">SpringBootApplication</a>
 * 
 */
@SpringBootApplication
public class ImdApplication {
        
    /**
     * This is main method of application.
     * This method will run spring boot application and initialize the spring configuration.
     * @param args is Command-Line Arguments
     */
    	public static void main(String[] args) {
		SpringApplication.run(ImdApplication.class, args);
	}
}
