package com.sobow.demo.beans.services.impl;

import com.sobow.demo.beans.services.RedPrinter;
import org.springframework.stereotype.Service;

@Service
public class EnglishRedPrinter implements RedPrinter {
    
    @Override
    public String print() {
        return "english: RED";
    }
}
