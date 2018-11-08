package net.azurewebsites.fatecsjc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import net.azurewebsites.fatecsjc.resources.test.DBTest;

@Configuration
@Profile("test")
public class TestConfig {
	@Autowired
	private DBTest dbTeste;
	
	@Bean
	public boolean instantiateDatabase(){
		
		dbTeste.initialize();
		
		return true;
	}

}
