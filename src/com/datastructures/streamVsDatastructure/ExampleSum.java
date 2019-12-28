package com.datastructures.streamVsDatastructure;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ExampleSum {
    public static void main(String[] args) {

        //найти сумму элементов находящихся в коллекции List
        List<Integer> list = new ArrayList<>();
        Random generator = new Random();
        for(int i = 0; i < 10; i++ ) {
            list.add(generator.nextInt(100));
        }
        list.forEach(System.out::println);
        int sum = list.stream().reduce((s1,s2)->s1+s2).orElse(0);
        System.out.println("sum elements list =  "+ sum );
        System.out.println("-----------------------------------------------------------------");

        long result = LongStream.range(2, 16).sum();
        System.out.println("sum elements LongStream =  "+ result );

        int result2 = IntStream.range(2, 20).sum();
        System.out.println("sum elements IntStream =  "+ result2 );
        System.out.println("-----------------------------------------------------------------");

        //найти сумму элементов находящихся в коллекции Map
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < 10; i++ ) {
            map.put(i,generator.nextInt(100));
        }
        map.entrySet().forEach(System.out::println);
        int sumValue = map.values().stream().reduce((s1,s2)->s1+s2).orElse(0);
        int sumKey = map.keySet().stream().reduce((s1,s2)->s1+s2).orElse(0);
        System.out.println("sum elements mapValue =  "+ sumValue );
        System.out.println("sum elements mapKey =  "+ sumKey );
        System.out.println("-----------------------------------------------------------------");

        //Возвращает сумму всех чисел
        List <Integer> list2 = Arrays.asList(2,12,34,67,24,65,88,56);
        int sum2 = list2.stream().mapToInt((s) -> Integer.parseInt(String.valueOf(s))).sum();
        System.out.println("Sum = "+ sum2);


    }
}
