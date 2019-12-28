package com.datastructures.streamVsDatastructure;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListExStream {

    public static void main(String[] args) {
        //Parallel Stream
        List<Integer> list = new ArrayList<>();
        list.parallelStream()
                .filter(x -> x > 10)
                .map(x -> x * 2)
                .collect(Collectors.toList());

        IntStream.range(0, 10)
                .parallel()
                .map(x -> x * 10)
                .sum();


    }

}
