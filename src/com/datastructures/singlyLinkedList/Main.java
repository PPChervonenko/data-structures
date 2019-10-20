package com.datastructures.singlyLinkedList;

import com.datastructures.singlyLinkedList.newVersionList.List;

public class Main {
    public static void main(String[] args) {
        SingleLinkedList  s= new SingleLinkedList();
        s.addBack(1);
        s.addBack(2);
        s.addBack(3);
        s.addBack(4);
        s.addBack("A");
        s.addBack("B");
        s.addFront("WWW");

        s.printList();
        System.out.println();
        s.reverseList();

    }
}
