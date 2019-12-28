package com.datastructures.streamVsDatastructure;

import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

enum Speciality {
    Biology, ComputerScience, Economics, Finance,
    History, Philosophy, Physics, Psychology
}
class Student{


    private String name;
    private Speciality speciality;
    private Integer Year;

    public Student(String name, Speciality speciality, Integer year) {
        this.name = name;
        this.speciality = speciality;
        Year = year;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return Year;
    }

    public void setYear(Integer year) {
        Year = year;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", speciality=" + speciality +
                ", Year=" + Year +
                '}';
    }
}

public class Examples {



    public static void main(String[] args) {
        //Дан массив аргументов. Нужно получить Map, где каждому ключу будет соответствовать своё значение.

        String[] arguments = {"-i", "in.txt", "--limit", "40", "-d", "1", "-o", "out.txt"};
        Map<String, String> argsMap = new LinkedHashMap<>(arguments.length / 2);
        for (int i = 0; i < arguments.length; i += 2) {
            argsMap.put(arguments[i], arguments[i + 1]);
        }
        argsMap.forEach((key, value) -> System.out.format("%s: %s%n", key, value));
        System.out.println("-----------------------------------------------------------");
        //А вот для обратной задачи — сконвертировать Map с аргументами в массив строк, стримы помогут.
        String[] args2 = argsMap.entrySet().stream()
                .flatMap(e -> Stream.of(e.getKey(), e.getValue()))
                .toArray(String[]::new);
        System.out.println(String.join(" ", args2));
        System.out.println("----------------------------------------------------------");

        //**************************Example Student*************************************************************

        List<Student> students = Arrays.asList(
                new Student("Alex", Speciality.Physics, 1),
                new Student("Rika", Speciality.Biology, 4),
                new Student("Julia", Speciality.Biology, 2),
                new Student("Steve", Speciality.History, 4),
                new Student("Mike", Speciality.Finance, 1),
                new Student("Hinata", Speciality.Biology, 2),
                new Student("Richard", Speciality.History, 1),
                new Student("Kate", Speciality.Psychology, 2),
                new Student("Sergey", Speciality.ComputerScience, 4),
                new Student("Maximilian", Speciality.ComputerScience, 3),
                new Student("Tim", Speciality.ComputerScience, 5),
                new Student("Ann", Speciality.Psychology, 1)
        );
        //Нужно сгруппировать всех студентов по курсу.
        students.stream()
                .collect(Collectors.groupingBy(Student::getYear))
                .entrySet().forEach(System.out::println);
        //Вывести в алфавитном порядке список специальностей, на которых учатся перечисленные в списке студенты
        System.out.println("----------------------------------------------------------");
        students.stream()
                .map(Student::getSpeciality)
                .distinct()
                .sorted(Comparator.comparing(Enum::name))
                .forEach(System.out::println);
        System.out.println("----------------------------------------------------------");
        //Вывести количество учащихся на каждой из специальностей.
        students.stream()
                .collect(Collectors.groupingBy(
                        Student::getSpeciality, Collectors.counting()))
                .forEach((s, count) -> System.out.println(s + ": " + count));
        System.out.println("----------------------------------------------------------");
        //Сгруппировать студентов по специальностям, сохраняя алфавитный порядок специальности, а затем сгруппировать
        // по курсу.
        Map<Speciality, Map<Integer, List<Student>>> result = students.stream()
                .sorted(Comparator
                        .comparing(Student::getSpeciality, Comparator.comparing(Enum::name))
                        .thenComparing(Student::getYear)
                )
                .collect(Collectors.groupingBy(
                        Student::getSpeciality,
                        LinkedHashMap::new,
                        Collectors.groupingBy(Student::getYear)));
        //выводим
        result.forEach((s, map) -> {
            System.out.println("-= " + s + " =-");
            map.forEach((year, list) -> System.out.format("%d: %s%n", year, list.stream()
                    .map(Student::getName)
                    .sorted()
                    .collect(Collectors.joining(", ")))
            );
            System.out.println();
        });
        System.out.println("----------------------------------------------------------");
        //Проверить, есть ли третьекурсники среди учащихся всех специальностей кроме физики и CS.
        students.stream()
                .filter(s -> !EnumSet.of(Speciality.ComputerScience, Speciality.Physics)
                        .contains(s.getSpeciality()))
                .anyMatch(s -> s.getYear() == 3);
        //**************************Example Student*************************************************************

        //Вычислить число Пи методом Монте-Карло.
        System.out.println("----------------------------------------------------------");
        final Random rnd = new Random();
        final double r = 1000.0;
        final int max = 10000000;
        long count = IntStream.range(0, max)
                .mapToObj(i -> rnd.doubles(2).map(x -> x * r).toArray())
                .parallel()
                .filter(arr -> Math.hypot(arr[0], arr[1]) <= r)
                .count();
        System.out.println(4.0 * count / max);
        System.out.println("----------------------------------------------------------");
        //Вывести таблицу умножения.
        IntStream.rangeClosed(2, 9)
                .boxed()
                .flatMap(i -> IntStream.rangeClosed(2, 9)
                        .mapToObj(j -> String.format("%d * %d = %d", i, j, i * j))
                )
                .forEach(System.out::println);
        System.out.println("----------------------------------------------------------");

        IntFunction<IntFunction<String>> function = i -> j -> String.format("%d x %2d = %2d", i, j, i * j);
        IntFunction<IntFunction<IntFunction<String>>> repeaterX = count2 -> i -> j ->
                IntStream.range(0, count2)
                        .mapToObj(delta -> function.apply(i + delta).apply(j))
                        .collect(Collectors.joining("\t"));
        IntFunction<IntFunction<IntFunction<IntFunction<String>>>> repeaterY = countY -> countX -> i -> j ->
                IntStream.range(0, countY)
                        .mapToObj(deltaY -> repeaterX.apply(countX).apply(i).apply(j + deltaY))
                        .collect(Collectors.joining("\n"));
        IntFunction<String> row = i -> repeaterY.apply(10).apply(4).apply(i).apply(1) + "\n";
        IntStream.of(2, 6).mapToObj(row).forEach(System.out::println);


    }
}
