package com.datastructures.singlyLinkedList;

public class ListElement<E> {

    private ListElement next;
    private E data;

    public ListElement getNext() {
        return next;
    }

    public void setNext(ListElement next) {
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

}
