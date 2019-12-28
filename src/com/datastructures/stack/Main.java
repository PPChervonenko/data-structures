package com.datastructures.stack;
public class Main {

    public static void main(String[] args) {
        Stack stack = new Stack();

        stack.push("2");
        stack.push("3");
        stack.push("5");

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
