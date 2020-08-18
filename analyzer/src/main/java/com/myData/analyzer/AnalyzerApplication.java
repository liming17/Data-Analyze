package com.myData.analyzer;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.myData.analyzer.entities.Role;
import com.myData.analyzer.entities.Stock;
import com.myData.analyzer.entities.User;
import com.myData.analyzer.repositories.UserRepository;
import com.myData.analyzer.services.StockService;
import com.myData.analyzer.services.UserService;


@SpringBootApplication
public class AnalyzerApplication {

	
    @Bean
    public CommandLineRunner setupDefaultUser(UserService service) {
        return args -> {
            service.save(new User(
                    "user1", //username
                    "user", //password
                    Arrays.asList(new Role("USER"), new Role("ADMIN")),//roles 
                    true//Active
            ));
            service.save(new User(
                    "user2", //username
                    "user", //password
                    Arrays.asList(new Role("USER"), new Role("ADMIN")),//roles 
                    true//Active
            ));
        };
    }
    
    @Bean
    public CommandLineRunner setupDefaultStocks(StockService service) {
        return args -> {
            service.saveStock(new Stock(
                    "BABA", //symbol
                    "Alibaba Group Holding Limited", //name
                    0
            ));
            service.saveStock(new Stock(
                    "AAPL", 
                    "Apple Inc.", 
                    0
            ));
            service.saveStock(new Stock(
                    "BILI", 
                    "Bilibili Inc.", 
                    0
            ));
            
            service.saveStock(new Stock(
                    "AMZN", 
                    "Amazon.com Inc.", 
                    0
            ));
            
            service.saveStock(new Stock(
                    "FB", 
                    "Facebook Inc.", 
                    0
            ));
        };
    }
    
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    } 
    
	public static void main(String[] args) {
		SpringApplication.run(AnalyzerApplication.class, args);
	}

}
