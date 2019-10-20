package com.datastructures.singlyLinkedList.newVersionList;

public class List<E>{
    private Node head;
    private Node tail;

    public void setList(E data){
        Node node = new Node();
        node.setData(data);
        if(head==null){
            head = node;
            tail = node;
        }else {
            node.setNext(head);
            head = node;
        }
    }
    public void setTailList(E data){
        Node node = new Node();
        node.setData(data);
        if(tail==null){
            head=node;
            head=node;

        }else{
            tail.setNext(node);
            tail = node;
        }
    }
    public void printList(){
        Node print = head;
        while (print != null){
            System.out.print(print.getData() + " ");
            print = print.getNext();
        }
    }
    public void deleteList(E data){
        if(head==null){
            return;
        }
        if(head==null){
            head=null;
            tail=null;
        }
        if(head.getData()==data){
            head.getNext();
            return;
        }
        Node search = head;
        while(search!=null){
            if (search.getNext().getData()==data) {
                if(tail == search.getNext()){
                    tail=search;
                }
                search.setNext(search.getNext().getNext());
                return;
            }
            search = search.getNext();
        }
    }
    public  void reversList(){
        Node Pointer1 = null; //Pointer1 = NULL (1)
        Node Pointer2;
        Node currentList = head;

        while (currentList != null){
            Pointer2 = currentList.getNext();  // Pointer2 = Head->next (2)
            currentList.setNext(Pointer1); // Head->next = Pointer1 (3)
            Pointer1 = currentList;  // Pointer1 = Head (4)
            currentList = Pointer2;  // Head = Pointer2 (5);
        }
        head = Pointer1;
        Node reversList = head;
        while (reversList!=null){
            System.out.print(reversList.getData() + " ");
            reversList = reversList.getNext();
        }

    }


}
