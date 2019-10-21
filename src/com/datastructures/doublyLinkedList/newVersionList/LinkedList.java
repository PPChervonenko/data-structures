package com.datastructures.doublyLinkedList.newVersionList;

public class LinkedList<E> {
    private Node next;
    private Node prev;

    public void  addLinkedList(E data){
        Node node = new Node();
        node.setData(data);
       if(next == null){
        next = node;
        prev = node;
        }else{

            next.setPrev(node);
            node.setNext(next);
            node.setPrev(null);
            next = node;
       }
    }
    public void displayList(){
            Node current = next;
            while (current != null) {
                System.out.print(current.getData() + "  ");
                current = current.getNext();
            }
            System.out.println();
        }

}
