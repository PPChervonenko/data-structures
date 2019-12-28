package com.datastructures.streamVsDatastructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorImplCollector {
    //Хорошей практикой является создание коллекторов, которые принимают ещё один коллектор и зависят от него.
    // Например, можно будет складывать элементы не только в List, но и в любую другую коллекцию
    // (Collectors.toCollection), либо в строку (Collectors.joining).

    public static <T, D, A> Collector<T, ?, Map<Boolean, D>> partitioningByUniqueness(
            Collector<? super T, A, D> downstream) {
        class Holder<A, B> {
            final A unique, repetitive;
            final B set;
            Holder(A unique, A repetitive, B set) {
                this.unique = unique;
                this.repetitive = repetitive;
                this.set = set;
            }
        }
        BiConsumer<A, ? super T> downstreamAccumulator = downstream.accumulator();
        BinaryOperator<A> downstreamCombiner = downstream.combiner();
        BiConsumer<Holder<A, Set<T>>, T> accumulator = (t, element) -> {
            A container = t.set.add(element) ? t.unique : t.repetitive;
            downstreamAccumulator.accept(container, element);
        };
        return Collector.<T, Holder<A, Set<T>>, Map<Boolean, D>>of(
                () -> new Holder<>(
                        downstream.supplier().get(),
                        downstream.supplier().get(),
                        new HashSet<>() ),
                accumulator,
                (t1, t2) -> {
                    downstreamCombiner.apply(t1.repetitive, t2.repetitive);
                    t2.set.forEach(e -> accumulator.accept(t1, e));
                    return t1;
                },
                t -> {
                    Map<Boolean, D> result = new HashMap<>(2);
                    result.put(Boolean.FALSE, downstream.finisher().apply(t.repetitive));
                    result.put(Boolean.TRUE, downstream.finisher().apply(t.unique));
                    t.set.clear();
                    return result;
                });
    }
//Алгоритм остался тем же, только теперь уже нельзя во второй контейнер сразу же складывать уникальные элементы,
// приходится создавать новый set. Для удобства также добавлен класс Holder, который хранит два контейнера для
// уникальных и повторяющихся элементов, а также само множество.
//
//Все операции теперь нужно проводить через переданный коллектор, именуемый downstream. Именно он сможет поставить
// контейнер нужного типа (downstream.supplier().get()), добавить элемент в этот контейнер
// (downstream.accumulator().accept(container, element)), объединить контейнеры и создать окончательный результат.
    public static void main(String[] args) {
        Stream.of(1, 2, 3, 1, 9, 2, 5, 3, 4, 8, 2)
                .map(String::valueOf)
                .collect(partitioningByUniqueness(Collectors.joining("-")))
                .forEach((isUnique, str) -> System.out.format("%s: %s%n", isUnique ? "unique" : "repetitive", str));
    }
}
