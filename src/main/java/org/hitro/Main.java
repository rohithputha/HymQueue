package org.hitro;

import org.hitro.services.HymQueue;

public class Main {
    public static void main(String[] args) {
        HymQueue hymQueue = new HymQueue();
        hymQueue.createChannel("testChannel");
        hymQueue.add("hello","testChannel","123");
        hymQueue.add(123,"testChannel","123");
        System.out.println(hymQueue.get("testChannel"));
        System.out.println(hymQueue.get("testChannel"));
        System.out.println(hymQueue.get("hello"));
    }
}