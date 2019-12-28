package com.datastructures.streamVsDatastructure;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


//Благодаря классу Spliterators, можно преобразовать любой итератор в сплитератор. Вот пример создания стрима из
// итератора, генерирующего бесконечную последовательность чисел Фибоначчи.
public class FibonacciIterator implements Iterator<BigInteger> {

    private BigInteger a = BigInteger.ZERO;
    private BigInteger b = BigInteger.ONE;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public BigInteger next() {
        BigInteger result = a;
        a = b;
        b = result.add(b);
        return result;
    }


    public static void main(String[] args) {
        StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        new FibonacciIterator(),
                        Spliterator.ORDERED | Spliterator.SORTED),
                false /* is parallel*/)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("-----------------------------------------------");
//Stream.iterate + map
//
//Можно воспользоваться двумя операторами: iterate + map, чтобы создать всё тот же стрим из чисел Фибоначчи.
        Stream.iterate(
                new BigInteger[] { BigInteger.ZERO, BigInteger.ONE },
                t -> new BigInteger[] { t[1], t[0].add(t[1]) })
                .map(t -> t[0])
                .limit(10)
                .forEach(System.out::println);
        System.out.println("-----------------------------------------------");

        //IntStream.range + map
        //
        //Ещё один гибкий и удобный способ создать стрим. Если у вас есть какие-то данные, которые можно получить по
        // индексу, то можно создать числовой промежуток при помощи оператора range, затем поэлементно с помощью него
        // обращаться к данным через map/mapToObj.
//        IntStream.range(0, 200)
//                .mapToObj(i -> fibonacci(i))
//                .forEach(System.out::println);
//
//        JSONArray arr = ...
//        IntStream.range(0, arr.length())
//                .mapToObj(JSONArray::getJSONObject)
//                .map(obj -> ...)
//    .forEach(System.out::println);
    }
}

