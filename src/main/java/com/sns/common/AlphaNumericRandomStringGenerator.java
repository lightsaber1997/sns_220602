package com.sns.common;


import java.util.Random;


public class AlphaNumericRandomStringGenerator extends RandomStringGenerator {
    private Random random;
    // this could be implemented as a static variable
    // but I'm concerned then it may not be thread safe
    // and may cause problem to the springboot application.
    private String alphaNum = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    
    public AlphaNumericRandomStringGenerator(Random random) {
        this.random = random;
    }

    @Override
    public String getRandomString11(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(alphaNum.charAt(random.nextInt(alphaNum.length())));
        }

        return sb.toString();
    }
}
