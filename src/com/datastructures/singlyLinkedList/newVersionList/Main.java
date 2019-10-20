package com.datastructures.singlyLinkedList.newVersionList;

public class Main {
    public static void main(String[] args) {
        List list = new List();
        list.setList(1);
        list.setList(2);
        list.setList(3);
        list.setList(4);
        list.setList("XXX");
        list.setTailList("E");

        list.printList();
        System.out.println();
        list.reversList();
        list.deleteList(2);
        System.out.println();
        list.printList();
    }
}
