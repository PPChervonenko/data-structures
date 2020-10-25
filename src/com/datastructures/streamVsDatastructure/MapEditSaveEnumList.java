package com.datastructures.streamVsDatastructure;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum Level{ Junior,Middle,Senior }
class Programer{
     private String name;
     private Integer age;

    public Programer(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Programer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class MapEditSaveEnumList {

    public static void main(String[] args) {

        List<Programer> programerList = new ArrayList<>();
        programerList.add(new Programer("Vasia", 25));
        Map<Level, List<Programer>> erumMap = new HashMap();

        erumMap.put(Level.Middle, Arrays.asList(new Programer("Dima",45)));
        erumMap.put(Level.Senior, Arrays.asList(new Programer("Kolia",35)));
        erumMap.put(Level.Junior,programerList);
        System.out.println("---------------------------------------");
        for(Map.Entry<Level, List<Programer>> item: erumMap.entrySet()){
                System.out.println("programmer ERUM: "+ item.getKey()+" "+item.getValue());
        }
        System.out.println("---------------------------------------");
        erumMap.entrySet().stream().forEach(System.out::println);

        System.out.println("---------------------------------------");
        Stream<Programer> programerStream = Stream.of(
                new Programer("Vasia", 39),
                new Programer("Kolia", 32),
                new Programer("Dima", 23)
        );
        Map<String, List<Programer>> erum2 = programerStream.collect(Collectors.groupingBy(Programer::getName));
        erum2.entrySet().forEach(System.out::println);

        System.out.println("---------------------------------------");
        EnumMap<Level,List<Programer>> erumMap2 = new EnumMap<>(Level.class);
        erumMap2.put(Level.Senior,Arrays.asList(new Programer("Robert",45)));
        erumMap2.put(Level.Middle,Arrays.asList(new Programer("Valera",34)));
        erumMap2.put(Level.Junior,Arrays.asList(new Programer("Leon",43)));

        erumMap2.entrySet().stream().forEach(System.out::println);
    }


}
