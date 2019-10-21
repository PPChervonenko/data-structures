package com.datastructures.queue;

import java.util.NoSuchElementException;

/*

 */
public class Queue<E> {
private Node<E> first;
private Node<E> last;

public void addQueue(E data){
Node node = new Node();
node.setData(data);
if(last!=null){
    last.setNext(node);
}
last=node;
if(first==null){
    first=last;
}
}
public E remove(){
    if(first==null)throw new NoSuchElementException();
    E data = first.getData();
    first = first.getNext();
    if(first==null){
        last=null;
    }
    return  data;
}
public  E peek(){
    if(first==null)throw new NoSuchElementException();
    return first.getData();
}
public  boolean isEmpty(){
    return  first==null;
}
}
