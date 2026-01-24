package com.arya;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.arya.config.AppConfig;

public class Main {
	public static void main(String[] args) {
		//create the container, bucket
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		//asking for bean, spring looks at container, sees authService() , 
//		creates, injects, give it
		AuthService service = context.getBean(AuthService.class);
		
		//use
		service.login("meow");
	}
}
