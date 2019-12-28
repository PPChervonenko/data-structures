package com.datastructures.streamVsDatastructure;

import java.util.ArrayList;
import java.util.List;

public class ListEditSaveArray {

    public static void main(String[] args) {

    // нахождение чисел кратных 3 и занесение их в массив
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(6);

       Integer[] r = list.stream().filter(s->s%3==0).toArray(Integer[]::new);
        for(int index=0; index < r.length ; index++)
            System.out.println(r[index]);
    }
}
