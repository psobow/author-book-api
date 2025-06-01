package com.sobow.demo.pizza;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pizza")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PizzaConfig {
    
    private String crust;
    private String Topping;
    private String sauce;
}
