package com.asan.cms.rpc;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class TransactionReferenceGeneratorImpl implements TransactionReferenceGenerator {
    private String getRandomDigit() {
        Random rand = new Random();
        return rand.nextInt(10) + "";
    }

    @Override
    public String getRandomRRN() {
        return (new Date().getTime() + getRandomDigit() + getRandomDigit()).substring(3, 15);
    }

    @Override
    public String getRandomRefTranId() {
        return new Date().getTime() + getRandomDigit();
    }
}