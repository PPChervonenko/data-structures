package com.datastructures.streamVsDatastructure;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MethodCollectorsStream {
    public static void main(String[] args) {

        //toList​()
        //Самый распространённый метод. Собирает элементы в List.
        //
        //
        //toSet​()
        //Собирает элементы в множество.
        //
        //
        //toCollection​(Supplier collectionFactory)
        //Собирает элементы в заданную коллекцию. Если нужно конкретно указать, какой List, Set или другую коллекцию мы хотим использовать, то этот метод поможет.

        Deque<Integer> deque = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.toCollection(ArrayDeque::new));

        Set<Integer> set = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        System.out.println("-----------------------------------------------");

        //toMap​(Function keyMapper, Function valueMapper)
        //Собирает элементы в Map. Каждый элемент преобразовывается в ключ и в значение, основываясь на результате
        // функций keyMapper и valueMapper соответственно. Если нужно вернуть тот же элемент, что и пришел,
        // то можно передать Function.identity().

        Map<Integer, Integer> map1 = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.toMap(
                        Function.identity(),
                        Function.identity()
                ));
        map1.forEach((integer, s) -> System.out.println(integer+ "->"+s));
        System.out.println("-----------------------------------------------");
        Map<Integer, String> map2 = Stream.of(1, 2, 3)
                .collect(Collectors.toMap(
                        Function.identity(),
                        i -> String.format("%d * 2 = %d", i, i * 2)
                ));
        map2.forEach((integer, s) -> System.out.println(integer+ "->"+s));
        System.out.println("-----------------------------------------------");
        Map<Character, String> map3 = Stream.of(50, 54, 55)
                .collect(Collectors.toMap(
                        i -> (char) i.intValue(),
                        i -> String.format("<%d>", i)
                ));
        map3.forEach((integer, s) -> System.out.println(integer+ "->"+s));
        System.out.println("-----------------------------------------------");

        //toMap​(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction)
        //Аналогичен первой версии метода, только в случае, когда встречается два одинаковых ключа,
        // позволяет объединить значения.

        Map<Integer, String> map4 = Stream.of(50, 55, 69, 20, 19, 52)
                .collect(Collectors.toMap(
                        i -> i % 5,
                        i -> String.format("<%d>", i),
                        (a, b) -> String.join(", ", a, b)
                ));
        map4.forEach((integer, s) -> System.out.println(integer+ "->"+s));
        System.out.println("-----------------------------------------------");

        //toMap​(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction, Supplier mapFactory)
    //Всё то же, только позволяет указывать, какой именно класс Map использовать.
        Map<Integer, String> map5 = Stream.of(50, 55, 69, 20, 19, 52)
                .collect(Collectors.toMap(
                        i -> i % 5,
                        i -> String.format("<%d>", i),
                        (a, b) -> String.join(", ", a, b),
                        LinkedHashMap::new
                ));
        map5.forEach((integer, s) -> System.out.println(integer+ "->"+s));
        //toConcurrentMap​(Function keyMapper, Function valueMapper)
        //toConcurrentMap​(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction)
        //toConcurrentMap​(Function keyMapper, Function valueMapper, BinaryOperator mergeFunction, Supplier mapFactory)
        //Всё то же самое, что и toMap, только работаем с ConcurrentMap.

        System.out.println("-----------------------------------------------");

        //collectingAndThen​(Collector downstream, Function finisher)
        //Собирает элементы с помощью указанного коллектора, а потом применяет к полученному результату функцию.

        List<Integer> list = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Collections::unmodifiableList));
        System.out.println(list.getClass());


        List<String> list2 = Stream.of("a", "b", "c", "d")
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(Function.identity(), s -> s + s),
                        map -> map.entrySet().stream()))
                .map(e -> e.toString())
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Collections::unmodifiableList));
        list2.forEach(System.out::println);
        System.out.println("-----------------------------------------------");
        //joining​()
        //joining​(CharSequence delimiter)
        //joining​(CharSequence delimiter, CharSequence prefix, CharSequence suffix)
        //Собирает элементы, реализующие интерфейс CharSequence, в единую строку.
        // Дополнительно можно указать разделитель, а также префикс и суффикс для всей последовательности.

        String s1 = Stream.of("a", "b", "c", "d")
                .collect(Collectors.joining());
        System.out.println(s1);


        String s2 = Stream.of("a", "b", "c", "d")
                .collect(Collectors.joining("-"));
        System.out.println(s2);


        String s3 = Stream.of("a", "b", "c", "d")
                .collect(Collectors.joining(" -> ", "[ ", " ]"));
        System.out.println(s3);

        System.out.println("-----------------------------------------------");

        //summingInt​(ToIntFunction mapper)
        //summingLong​(ToLongFunction mapper)
        //summingDouble​(ToDoubleFunction mapper)
        //Коллектор, который преобразовывает объекты в int/long/double и подсчитывает сумму.
        //
        //averagingInt​(ToIntFunction mapper)
        //averagingLong​(ToLongFunction mapper)
        //averagingDouble​(ToDoubleFunction mapper)
        //Аналогично, но со средним значением.
        //
        //summarizingInt​(ToIntFunction mapper)
        //summarizingLong​(ToLongFunction mapper)
        //summarizingDouble​(ToDoubleFunction mapper)
        //Аналогично, но с полной статистикой.

        Integer sum = Stream.of("1", "2", "3", "4")
                .collect(Collectors.summingInt(Integer::parseInt));
        System.out.println(sum);


        Double average = Stream.of("1", "2", "3", "4")
                .collect(Collectors.averagingInt(Integer::parseInt));
        System.out.println(average);


        DoubleSummaryStatistics stats = Stream.of("1.1", "2.34", "3.14", "4.04")
                .collect(Collectors.summarizingDouble(Double::parseDouble));
        System.out.println(stats);

        System.out.println("-----------------------------------------------");

        //counting​()
        //Подсчитывает количество элементов.

        Long count = Stream.of("1", "2", "3", "4")
                .collect(Collectors.counting());
        System.out.println(count);
        System.out.println("-----------------------------------------------");

        //filtering​(Predicate predicate, Collector downstream)
        //mapping​(Function mapper, Collector downstream)
        //flatMapping​(Function downstream)
        //reducing​(BinaryOperator op)
        //reducing​(T identity, BinaryOperator op)
        //reducing​(U identity, Function mapper, BinaryOperator op)
        //Специальная группа коллекторов, которая применяет операции filter, map, flatMap и reduce.
        // filtering​ и flatMapping​ появились в Java 9.
        List<Integer> ints = Stream.of(1, 2, 3, 4, 5, 6)
                .collect(Collectors.filtering(
                        x -> x % 2 == 0,
                        Collectors.toList()));


        String m1 = Stream.of(1, 2, 3, 4, 5, 6)
                .collect(Collectors.filtering(
                        x -> x % 2 == 0,
                        Collectors.mapping(
                                x -> Integer.toString(x),
                                Collectors.joining("-")
                        )
                ));


        String m2 = Stream.of(2, 0, 1, 3, 2)
                .collect(Collectors.flatMapping(
                        x -> IntStream.range(0, x).mapToObj(Integer::toString),
                        Collectors.joining(", ")
                ));


        int value = Stream.of(1, 2, 3, 4, 5, 6)
                .collect(Collectors.reducing(
                        0, (a, b) -> a + b
                ));

        String m3 = Stream.of(1, 2, 3, 4, 5, 6)
                .collect(Collectors.reducing(
                        "", x -> Integer.toString(x), (a, b) -> a + b
                ));
        System.out.println("-----------------------------------------------");

        //minBy​(Comparator comparator)
        //maxBy​(Comparator comparator)
        //Поиск минимального/максимального элемента, основываясь на заданном компараторе.
        Optional<String> min = Stream.of("ab", "c", "defgh", "ijk", "l")
                .collect(Collectors.minBy(Comparator.comparing(String::length)));
        min.ifPresent(System.out::println);


        Optional<String> max = Stream.of("ab", "c", "defgh", "ijk", "l")
                .collect(Collectors.maxBy(Comparator.comparing(String::length)));
        max.ifPresent(System.out::println);
        System.out.println("-----------------------------------------------");

        //groupingBy​(Function classifier)
        //groupingBy​(Function classifier, Collector downstream)
        //groupingBy​(Function classifier, Supplier mapFactory, Collector downstream)
        //Группирует элементы по критерию, сохраняя результат в Map. Вместе с представленными выше агрегирующими
        // коллекторами, позволяет гибко собирать данные. Подробнее о комбинировании в разделе Примеры.
        //
        //groupingByConcurrent​(Function classifier)
        //groupingByConcurrent​(Function classifier, Collector downstream)
        //groupingByConcurrent​(Function classifier, Supplier mapFactory, Collector downstream)
        //Аналогичный набор методов, только сохраняет в ConcurrentMap.

        Map<Integer, List<String>> map6 = Stream.of(
                "ab", "c", "def", "gh", "ijk", "l", "mnop")
                .collect(Collectors.groupingBy(String::length));
        map6.entrySet().forEach(System.out::println);

        System.out.println("-----------------------------------------------");
        Map<Integer, String> map7 = Stream.of(
                "ab", "c", "def", "gh", "ijk", "l", "mnop")
                .collect(Collectors.groupingBy(
                        String::length,
                        Collectors.mapping(
                                String::toUpperCase,
                                Collectors.joining())
                ));
        map7.entrySet().forEach(System.out::println);
        System.out.println("-----------------------------------------------");
        Map<Integer, List<String>> map8 = Stream.of(
                "ab", "c", "def", "gh", "ijk", "l", "mnop")
                .collect(Collectors.groupingBy(
                        String::length,
                        LinkedHashMap::new,
                        Collectors.mapping(
                                String::toUpperCase,
                                Collectors.toList())
                ));
        map8.entrySet().forEach(System.out::println);
        System.out.println("-----------------------------------------------");

        //partitioningBy​(Predicate predicate)
        //partitioningBy​(Predicate predicate, Collector downstream)
        //Ещё один интересный метод. Разбивает последовательность элементов по какому-либо критерию.
        // В одну часть попадают все элементы, которые удовлетворяют переданному условию, во вторую — все,
        // которые не удовлетворяют.

        Map<Boolean, List<String>> map9 = Stream.of(
                "ab", "c", "def", "gh", "ijk", "l", "mnop")
                .collect(Collectors.partitioningBy(s -> s.length() <= 2));
        map9.entrySet().forEach(System.out::println);
        System.out.println("-----------------------------------------------");

        Map<Boolean, String> map10= Stream.of(
                "ab", "c", "def", "gh", "ijk", "l", "mnop")
                .collect(Collectors.partitioningBy(
                        s -> s.length() <= 2,
                        Collectors.mapping(
                                String::toUpperCase,
                                Collectors.joining())
                ));
        map10.entrySet().forEach(System.out::println);
        System.out.println("-----------------------------------------------");

    }
}
