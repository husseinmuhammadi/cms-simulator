package com.asan.cms.rpc;

import java.util.Date;
import java.util.Random;

public class TransactionReferenceGenerator {
    private String getRandomDigit() {
        Random rand = new Random();
        return rand.nextInt(10) + "";
    }

    protected String getRandomRRN() {
        return (new Date().getTime() + getRandomDigit() + getRandomDigit()).substring(3, 15);
    }

    protected String getRandomRefTranId() {
        return new Date().getTime() + getRandomDigit();
    }
}