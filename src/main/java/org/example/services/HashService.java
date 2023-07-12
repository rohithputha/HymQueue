package org.example.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashService {
    private MessageDigest md;
    public HashService() throws NoSuchAlgorithmException {
        this.md = MessageDigest.getInstance("SHA-1");
    }
    public String getHashVal(String input){
        byte[] message = md.digest(input.getBytes());
        BigInteger signum = new BigInteger(1, message);
        StringBuilder initHash = new StringBuilder(signum.toString(16));
        while (initHash.length() < 32) {
            initHash.insert(0,"0");
        }
        return initHash.toString();
    }
}
