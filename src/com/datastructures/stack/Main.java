package com.datastructures.stack;
public class Main {

    public static void main(String[] args) {
        StackV1 stackV1 = new StackV1();

        stackV1.push("2");
        stackV1.push("3");
        stackV1.push("5");

        while (!stackV1.isEmpty()) {
            System.out.println(stackV1.pop());
        }
    }
}
