package com.datastructures.streamVsDatastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Optional;
import java.util.stream.*;

public class OperatorStream {

public static void main(String[] args) {

    //****************************************************
    //Sources - Источники
    //****************************************************

    //empty()
//Стрим, как и коллекция, может быть пустым, а значит всем последующем операторам нечего будет обрабатывать.
    Stream.empty().forEach(System.out::println);

    //of(T value)
    //of(T... values)
    //Стрим для одного или нескольких перечисленных элементов.
    Stream.of(1, 2, 3).forEach(System.out::println);
    System.out.println("--------------------------------");
    //ofNullable(T t)
    //Появился в Java 9. Возвращает пустой стрим, если в качестве аргумента передан null, в противном случае, возвращает стрим из одного элемента.
    String str = Math.random() > 0.5 ? "I'm feeling lucky" : null;
    Stream.ofNullable(str)
            .forEach(System.out::println);

    //generate(Supplier s)
    //Возвращает стрим с бесконечной последовательностью элементов, генерируемых функцией Supplier s.
    Stream.generate(() -> 6)
            .limit(6)
            .forEach(System.out::println);
    System.out.println("--------------------------------");
    //iterate​(T seed, UnaryOperator f)
    //Возвращает бесконечный стрим с элементами, которые образуются в результате последовательного
    // применения функции f к итерируемому значению. Первым элементом будет seed, затем f(seed), затем f(f(seed)) и так далее.
    Stream.iterate(2, x -> x + 6)
            .limit(6)
            .forEach(System.out::println);
    System.out.println("--------------------------------");

    //iterate​(T seed, Predicate hasNext, UnaryOperator f)
    //Появился в Java 9. Всё то же самое, только добавляется ещё один аргумент hasNext: если он возвращает false, то стрим завершается.

    Stream.iterate(2, x -> x < 25, x -> x + 6)
            .forEach(System.out::println);
    System.out.println("--------------------------------");

    //concat(Stream a, Stream b)
    //Объединяет два стрима так, что вначале идут элементы стрима A, а по его окончанию последуют элементы стрима B.

    Stream.concat(
            Stream.of(1, 2, 3),
            Stream.of(4, 5, 6))
            .forEach(System.out::println);
    System.out.println("--------------------------------");

    //builder()
    //Создаёт мутабельный объект для добавления элементов в стрим без использования какого-либо контейнера для этого.

    Stream.Builder<Integer> streamBuider = Stream.<Integer>builder()
            .add(0)
            .add(1);
    for (int i = 2; i <= 8; i += 2) {
        streamBuider.accept(i);
    }
    streamBuider
            .add(9)
            .add(10)
            .build()
            .forEach(System.out::println);

    System.out.println("--------------------------------");

    //IntStream.range​(int startInclusive, int endExclusive)
    //LongStream.range​(long startInclusive, long endExclusive)
    //Создаёт стрим из числового промежутка [start..end), то есть от start (включительно) по end.
    IntStream.range(0, 10).forEach(System.out::println);

    LongStream.range(-10L, -5L).forEach(System.out::println);
    System.out.println("--------------------------------");

    //IntStream.rangeClosed​(int startInclusive, int endInclusive)
    //LongStream.range​Closed(long startInclusive, long endInclusive)
    //Создаёт стрим из числового промежутка [start..end], то есть от start (включительно) по end (включительно).

    IntStream.rangeClosed(0, 5).forEach(System.out::println);
    LongStream.range(-8L, -5L).forEach(System.out::println);
    System.out.println("*******************Промежуточные операторы********************");
    //****************************************************
    //IntermediateOperators - Промежуточные операторы
    //****************************************************

    //filter​(Predicate predicate)
    //Фильтрует стрим, принимая только те элементы, которые удовлетворяют заданному условию.

    Stream.of(1, 2, 3)
            .filter(x -> x == 10)
            .forEach(System.out::print);
    // Вывода нет, так как после фильтрации стрим станет пустым

    Stream.of(120, 410, 85, 32, 314, 12)
            .filter(x -> x > 100)
            .forEach(System.out::println);

    IntStream.range(2, 9)
            .filter(x -> x % 3== 0)
            .forEach(System.out::println);

    System.out.println("--------------------------------");

    //map​(Function mapper)
    //Применяет функцию к каждому элементу и затем возвращает стрим, в котором элементами будут результаты функции. map можно применять для изменения типа элементов.
    //
    //Stream.mapToDouble​(ToDoubleFunction mapper)
    //Stream.mapToInt​(ToIntFunction mapper)
    //Stream.mapToLong​(ToLongFunction mapper)
    //IntStream.mapToObj(IntFunction mapper)
    //IntStream.mapToLong(IntToLongFunction mapper)
    //IntStream.mapToDouble(IntToDoubleFunction mapper)
    //
    //Специальные операторы для преобразования объектного стрима в примитивный, примитивного в объектный, либо примитивного стрима одного типа в примитивный стрим другого.

    Stream.of("3", "4", "5")
            .map(Integer::parseInt)
            .map(x -> x + 10)
            .forEach(System.out::println);


    Stream.of(120, 410, 85, 32, 314, 12)
            .map(x -> x + 11)
            .forEach(System.out::println);

    Stream.of("10", "11", "12")
                    .map(x -> Integer.parseInt(x, 16))
                    .forEach(System.out::println);
    System.out.println("--------------------------------");

    //flatMap​(Function<T, Stream<R>> mapper)
    //Один из самых интересных операторов. Работает как map, но с одним отличием — можно преобразовать один элемент в ноль, один или множество других.
    //
    //flatMapToDouble​(Function mapper)
    //flatMapToInt​(Function mapper)
    //flatMapToLong​(Function mapper)
    //
    //Как и в случае с map, служат для преобразования в примитивный стрим.
    //
    //Для того, чтобы один элемент преобразовать в ноль элементов, нужно вернуть null, либо пустой стрим. Чтобы преобразовать в один элемент,
    // нужно вернуть стрим из одного элемента, например, через Stream.of(x). Для возвращения нескольких элементов, можно любыми способами создать стрим с этими элементами.

    Stream.of(2, 3, 0, 1, 3)
            .flatMapToInt(x -> IntStream.range(0, x))
            .forEach(System.out::println);
    System.out.println("--------------------------------");
    Stream.of(1, 2, 3, 4, 5, 6)
            .flatMap(x -> {
                switch (x % 3 ) {
                    case 0:
                        return Stream.of(x, x*x, x*x*2);
                    case 1:
                        return Stream.of(x);
                    case 2:
                    default:
                        return Stream.empty();
                }
            })
            .forEach(System.out::println);
    System.out.println("--------------------------------");
    //limit​(long maxSize)
    //Ограничивает стрим maxSize элементами.
    Stream.of(120, 410, 85, 32, 314, 12)
            .limit(4)
            .forEach(System.out::println);
    System.out.println("--------------------------------");

    //skip​(long n)
    //Пропускает n элементов стрима.

    Stream.of(5, 10)
            .skip(40)
            .forEach(System.out::println);
    // Вывода нет

    Stream.of(120, 410, 85, 32, 314, 12)
            .skip(2)
            .forEach(System.out::println);
    System.out.println("--------------------------------");

    //sorted​()
    //sorted​(Comparator comparator)
    //Сортирует элементы стрима. Причём работает этот оператор очень хитро: если стрим уже помечен как отсортированный,
    // то сортировка проводиться не будет, иначе соберёт все элементы, отсортирует их и вернёт новый стрим,
    // помеченный как отсортированный
    Stream.of(120, 410, 85, 32, 314, 12)
            .sorted()
            .forEach(System.out::println);
    System.out.println("--------------------------------");

    //distinct​()
    //Убирает повторяющиеся элементы и возвращаем стрим с уникальными элементами.
    // Как и в случае с sorted, смотрит, состоит ли уже стрим из уникальных элементов и если это не так,
    // отбирает уникальные и помечает стрим как содержащий уникальные элементы.
    Stream.of(2, 1, 8, 1, 3, 2).distinct().forEach(System.out::println);

    System.out.println("--------------------------------");

    //peek​(Consumer action)
    //Выполняет действие над каждым элементом стрима и при этом возвращает стрим с элементами исходного стрима.
    // Служит для того, чтобы передать элемент куда-нибудь, не разрывая при этом цепочку операторов (вы же помните,
    // что forEach — терминальный оператор и после него стрим завершается?), либо для отладки.

    Stream.of(0, 3, 0, 0, 5)
            .peek(x -> System.out.format("before distinct: %d%n", x))
            .distinct()
            .peek(x -> System.out.format("after distinct: %d%n", x))
            .map(x -> x * x)
            .forEach(x -> System.out.format("after map: %d%n", x));

    //takeWhile​(Predicate predicate)
    //Появился в Java 9. Возвращает элементы до тех пор, пока они удовлетворяют условию,
    // то есть функция-предикат возвращает true. Это как limit, только не с числом, а с условием.
    Stream.of(1, 2, 3, 4, 2, 5)
            .takeWhile(x -> x < 3)
            .forEach(System.out::println);
    System.out.println("--------------------------------");

    //dropWhile​(Predicate predicate)
    //Появился в Java 9. Пропускает элементы до тех пор, пока они удовлетворяют условию,
    // затем возвращает оставшуюся часть стрима. Если предикат вернул для первого элемента false,
    // то ни единого элемента не будет пропущено. Оператор подобен skip, только работает по условию.

    Stream.of(1, 2, 3, 4, 2, 5)
            .dropWhile(x -> x >= 3)
            .forEach(System.out::println);

    Stream.of(1, 2, 3, 4, 2, 5)
            .dropWhile(x -> x < 3)
            .forEach(System.out::println);

    System.out.println("--------------------------------");

    //boxed()
    //Преобразует примитивный стрим в объектный.
    DoubleStream.of(0.1, Math.PI)
            .boxed()
            .map(Object::getClass)
            .forEach(System.out::println);

    System.out.println("--------------------------------");

    //****************************************************
    //Terminal operators - Терминальные операторы
    //****************************************************

    //void forEach​(Consumer action)
    //Выполняет указанное действие для каждого элемента стрима.

    Stream.of(120, 410, 85, 32, 314, 12)
            .forEach(x -> System.out.format("%s, ", x));

    System.out.println("--------------------------------");

   //void forEachOrdered​(Consumer action)
    //Тоже выполняет указанное действие для каждого элемента стрима,
    // но перед этим добивается правильного порядка вхождения элементов.
    // Используется для параллельных стримов, когда нужно получить правильную последовательность элементов.

    IntStream.range(0, 100000)
            .parallel()
            .filter(x -> x % 10000 == 0)
            .map(x -> x / 10000)
            .forEachOrdered(System.out::println);
    System.out.println("--------------------------------");

    //long count​()
    //Возвращает количество элементов стрима.
    long count = IntStream.range(0, 10)
            .flatMap(x -> IntStream.range(0, x))
            .count();
    System.out.println(count);

    System.out.println("--------------------------------");
    //R collect​(Collector collector)
    //Один из самых мощных операторов Stream API. С его помощью можно собрать все элементы в список,
    // множество или другую коллекцию, сгруппировать элементы по какому-нибудь критерию,
    // объединить всё в строку и т.д.. В классе java.util.stream.Collectors очень много методов на все случаи жизни,
    // мы рассмотрим их позже. При желании можно написать свой коллектор, реализовав интерфейс Collector.

    List<Integer> list = Stream.of(1, 2, 3)
            .collect(Collectors.toList());

    String s = Stream.of(1, 2, 3)
            .map(String::valueOf)
            .collect(Collectors.joining("-", "<", ">"));

    System.out.println("--------------------------------");

    //R collect​(Supplier supplier, BiConsumer accumulator, BiConsumer combiner)
    //То же, что и collect(collector), только параметры разбиты для удобства. Если нужно быстро сделать какую-то
    // операцию, нет нужды реализовывать интерфейс Collector, достаточно передать три лямбда-выражения.
    //
    //supplier должен поставлять новые объекты (контейнеры), например new ArrayList(), accumulator добавляет элемент в
    // контейнер, combiner необходим для параллельных стримов и объединяет части стрима воедино.

    List<String> list2 = Stream.of("a", "b", "c", "d")
            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    System.out.println("--------------------------------");
    //Object[] toArray​()
    //Возвращает нетипизированный массив с элементами стрима.
    //
    //A[] toArray​(IntFunction<A[]> generator)
    //Аналогично, только возвращает типизированный массив.



    String[] elements = Stream.of("a", "b", "c", "d")
            .toArray(String[]::new);

    System.out.println("--------------------------------");

    //T reduce​(T identity, BinaryOperator accumulator)
    //U reduce​(U identity, BiFunction accumulator, BinaryOperator combiner)
    //Ещё один полезный оператор. Позволяет преобразовать все элементы стрима в один объект. Например, посчитать сумму
    // всех элементов, либо найти минимальный элемент.
    //
    //Сперва берётся объект identity и первый элемент стрима, применяется функция accumulator и identity становится
    // её результатом. Затем всё продолжается для остальных элементов.

    int sum = Stream.of(1, 2, 3, 4, 5)
            .reduce(10, (acc, x) -> acc + x);
    System.out.println("--------------------------------");

    //Optional reduce​(BinaryOperator accumulator)
    //Этот метод отличается тем, что у него нет начального объекта identity. В качестве него служит первый элемент
    // стрима. Поскольку стрим может быть пустой и тогда identity объект не присвоится, то результатом функции служит
    // Optional, позволяющий обработать и эту ситуацию, вернув Optional.empty().

    Optional<Integer> result = Stream.<Integer>empty()
            .reduce((acc, x) -> acc + x);
    System.out.println(result.isPresent());


    Optional<Integer> sum2 = Stream.of(1, 2, 3, 4, 5)
            .reduce((acc, x) -> acc + x);
    System.out.println(sum2.get());

    System.out.println("--------------------------------");

    //Optional min​(Comparator comparator)
    //Optional max​(Comparator comparator)
    //Поиск минимального/максимального элемента, основываясь на переданном компараторе. Внутри вызывается reduce:

    //reduce((a, b) -> comparator.compare(a, b) <= 0 ? a : b));
    //reduce((a, b) -> comparator.compare(a, b) >= 0 ? a : b));
    int min = Stream.of(20, 11, 45, 78, 13)
            .min(Integer::compare).get();
    System.out.println("--------------------------------");

    //Optional findAny​()
    //Возвращает первый попавшийся элемент стрима. В параллельных стримах это может быть действительно любой элемент,
    // который лежал в разбитой части последовательности.
    //
    //Optional findFirst​()
    //Гарантированно возвращает первый элемент стрима, даже если стрим параллельный.
    //
    //Если нужен любой элемент, то для параллельных стримов быстрее будет работать findAny().

    int anySeq = IntStream.range(4, 65536)
            .findAny()
            .getAsInt();

    int firstSeq = IntStream.range(4, 65536)
            .findFirst()
            .getAsInt();

    int anyParallel = IntStream.range(4, 65536)
            .parallel()
            .findAny()
            .getAsInt();

    int firstParallel = IntStream.range(4, 65536)
            .parallel()
            .findFirst()
            .getAsInt();

    System.out.println("--------------------------------");

    //boolean allMatch​(Predicate predicate)
    //Возвращает true, если все элементы стрима удовлетворяют условию predicate.
    // Если встречается какой-либо элемент, для которого результат вызова функции-предиката будет false,
    // то оператор перестаёт просматривать элементы и возвращает false.

    boolean result2 = Stream.of(1, 2, 3, 4, 5)
            .allMatch(x -> x <= 7);
    System.out.println("--------------------------------");

    //boolean anyMatch​(Predicate predicate)
    //Возвращает true, если хотя бы один элемент стрима удовлетворяет условию predicate. Если такой элемент встретился,
    // нет смысла продолжать перебор элементов, поэтому сразу возвращается результат.
    boolean result3 = Stream.of(1, 2, 3, 4, 5)
            .anyMatch(x -> x == 3);

    System.out.println("--------------------------------");


    //boolean noneMatch​(Predicate predicate)
    //Возвращает true, если, пройдя все элементы стрима, ни один не удовлетворил условию predicate.
    // Если встречается какой-либо элемент, для которого результат вызова функции-предиката будет true,
    // то оператор перестаёт перебирать элементы и возвращает false.
    boolean resul4 = Stream.of(1, 2, 3, 4, 5)
            .noneMatch(x -> x == 9);

    System.out.println("--------------------------------");

    //OptionalDouble average​()
    //Только для примитивных стримов. Возвращает среднее арифметическое всех элементов. Либо Optional.empty,
    // если стрим пуст.
    double result5 = IntStream.range(2, 16)
            .average()
            .getAsDouble();
    System.out.println("--------------------------------");
    //sum()
    //Возвращает сумму элементов примитивного стрима. Для IntStream результат будет типа int, для LongStream — long,
    // для DoubleStream — double.
    long result6 = LongStream.range(2, 16)
            .sum();

    System.out.println("--------------------------------");

    //IntSummaryStatistics summaryStatistics()
    //Полезный метод примитивных стримов. Позволяет собрать статистику о числовой последовательности стрима,
    // а именно: количество элементов, их сумму, среднее арифметическое, минимальный и максимальный элемент.

    LongSummaryStatistics stats = LongStream.range(2, 16)
            .summaryStatistics();
    System.out.format("  count: %d%n", stats.getCount());
    System.out.format("    sum: %d%n", stats.getSum());
    System.out.format("average: %.1f%n", stats.getAverage());
    System.out.format("    min: %d%n", stats.getMin());
    System.out.format("    max: %d%n", stats.getMax());


}
}

