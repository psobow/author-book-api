package com.sobow.demo.beans.config;

import com.sobow.demo.beans.services.BluePrinter;
import com.sobow.demo.beans.services.ColourPrinter;
import com.sobow.demo.beans.services.GreenPrinter;
import com.sobow.demo.beans.services.RedPrinter;
import com.sobow.demo.beans.services.impl.ColourPrinterImpl;
import com.sobow.demo.beans.services.impl.SpanishBluePrinter;
import com.sobow.demo.beans.services.impl.SpanishGreenPrinter;
import com.sobow.demo.beans.services.impl.SpanishRedPrinter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrinterConfig {
    
    @Bean
    public BluePrinter bluePrinter() {
        return new SpanishBluePrinter();
    }
    
    @Bean
    public RedPrinter redPrinter() {
        return new SpanishRedPrinter();
    }
    
    @Bean
    public GreenPrinter greenPrinter() {
        return new SpanishGreenPrinter();
    }
    
    @Bean
    public ColourPrinter colourPrinter(RedPrinter redPrinter, BluePrinter bluePrinter, GreenPrinter greenPrinter) {
        return new ColourPrinterImpl(redPrinter,bluePrinter,greenPrinter);
    }
}
