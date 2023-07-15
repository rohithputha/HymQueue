package org.hitro.services;

import org.hitro.exceptions.HymQueueException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashService {
    private MessageDigest md;
    private HashService() throws NoSuchAlgorithmException {
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
    private static HashService hashService;
    public synchronized static HashService getInstance(){
        if(hashService == null){
            try{
                hashService = new HashService();
            }
            catch (Exception e){
                throw new HymQueueException("Hash algorithm not found.",e);
            }
        }
        return hashService;
    }
}
