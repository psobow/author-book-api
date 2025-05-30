package com.sobow.demo.beans.services.impl;

import com.sobow.demo.beans.services.BluePrinter;
import org.springframework.stereotype.Service;

@Service
public class EnglishBluePrinter implements BluePrinter {
    
    @Override
    public String print() {
        return "english: BLUE";
    }
}
