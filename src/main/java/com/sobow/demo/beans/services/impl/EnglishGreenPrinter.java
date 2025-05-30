package com.sobow.demo.beans.services.impl;

import com.sobow.demo.beans.services.GreenPrinter;
import org.springframework.stereotype.Service;

@Service
public class EnglishGreenPrinter implements GreenPrinter {
    
    @Override
    public String print() {
        return "english: GREEN";
    }
}
