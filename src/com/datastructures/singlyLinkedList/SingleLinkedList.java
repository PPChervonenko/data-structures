package com.datastructures.singlyLinkedList;

public class SingleLinkedList<E> {
    private ListElement head;       // указатель на первый элемент
    private ListElement tail;       // указатель последний элемент

    void addFront(E data)           //добавить спереди
    {
        ListElement a = new ListElement();  //создаём новый элемент
        a.setData(data);              //инициализируем данные.
        // указатель на следующий элемент автоматически инициализируется как null
        if(head.equals(null))            //если список пуст
        {                           //то указываем ссылки начала и конца на новый элемент
            head = a;               //т.е. список теперь состоит из одного элемента
            tail = a;
        }
        else {
            a.setNext(head);          //иначе новый элемент теперь ссылается на "бывший" первый
            head = a;               //а указатель на первый элемент теперь ссылается на новый элемент 
        }
    }

    void addBack(E data) {          //добавление в конец списка
        ListElement a = new ListElement();  //создаём новый элемент
        a.setData(data);
        if (tail==null)           //если список пуст
        {                           //то указываем ссылки начала и конца на новый элемент
            head = a;               //т.е. список теперь состоит из одного элемента
            tail = a;
        } else {
            tail.setNext(a);          //иначе "старый" последний элемент теперь ссылается на новый
            tail = a;               //а в указатель на последний элемент записываем адрес нового элемента
        }
    }

    void printList()                //печать списка
    {
        ListElement t = head;       //получаем ссылку на первый элемент   
        while (t != null)           //пока элемент существуе
        {
            System.out.print(t.getData() + " "); //печатаем его данные
            t = t.getNext();                     //и переключаемся на следующий
        }
    }

    void delEl(E data)          //удаление элемента
    {
        if(head == null)        //если список пуст - 
            return;             //ничего не делаем

        if (head == tail) {     //если список состоит из одного элемента
            head = null;        //очищаем указатели начала и конца
            tail = null;
            return;             //и выходим
        }

        if (head.getData() == data) {    //если первый элемент - тот, что нам нужен
            head = head.getNext();       //переключаем указатель начала на второй элемент
            return;                 //и выходим
        }

        ListElement t = head;       //иначе начинаем искать
        while (t.getNext() != null) {    //пока следующий элемент существует
            if (t.getNext().getData() == data) {  //проверяем следующий элемент
                if(tail == t.getNext())      //если он последний
                {
                    tail = t;           //то переключаем указатель на последний элемент на текущий
                }
                t.setNext(t.getNext().getNext());   //найденный элемент выкидываем
                return;                 //и выходим
            }
            t = t.getNext();                //иначе ищем дальше
        }
    }

    public void reverseList() {

        ListElement reversedPart = null;
        ListElement current = head;
        while (current != null) {
            ListElement next = current.getNext();
            current.setNext(reversedPart);
            reversedPart = current;
            current = next;
        }
        head = reversedPart;
        ListElement rever = head;
        while (rever != null)
        {
            System.out.print(rever.getData() + " ");
            rever = rever.getNext();
        }
    }

}