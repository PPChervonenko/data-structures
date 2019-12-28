package com.datastructures.streamVsDatastructure;

import java.util.stream.IntStream;

public class ArrayExStream {

    public static void main(String[] args) {
        IntStream.of(12, 23, 54, 57, 58, 45)
                .filter(x -> x < 46)
                .map(x -> x + 11)
                .limit(3)
                .forEach(System.out::println);


    }
}
