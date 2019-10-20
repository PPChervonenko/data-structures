package com.datastructures.doublyLinkedList;

public class Main {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList =  new DoubleLinkedList();
        String[] asd = {"1","2", "3", "4", "5"};
        doubleLinkedList.add("AAA");
        doubleLinkedList.addAll(asd);
        doubleLinkedList.printList();
    }


}
