package com.datastructures.stack;

import java.util.EmptyStackException;

public class StackV1 {
    private static class StackNode<T>{
        private T data;
        private StackNode<T> next;

        public StackNode(T data) {
            this.data = data;
        }
    }

    private StackNode<T> top;

    public Т pop(){
        if(top == null)throw new EmptyStackException();
        Т item = top.data ;
         top = top.next;
        return item;
       }
    public void push(T item) {
        StackNode <T> t = new StackNode<T> ( item ) ;
        t.next = top;
        top = t;
        }

    public Т peek ( ) {
        if (top == null) throw new EmptyStackException ( ) ;
        return top.data;
        }
    public boolean isEmpty ( ) {
    return top == null;
    }

}
