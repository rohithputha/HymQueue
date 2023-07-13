package org.hitro;

import org.hitro.services.Queue;

public class Main {
    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.createChannel("testChannel");
        queue.add("hello","testChannel","123");
        queue.add(123,"testChannel","123");
        System.out.println(queue.get("testChannel"));
        System.out.println(queue.get("testChannel"));
        System.out.println(queue.get("hello"));
    }
}