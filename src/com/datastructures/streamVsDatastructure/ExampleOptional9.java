package com.datastructures.streamVsDatastructure;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ExampleOptional9 {
    private static Map<String, String> survivors = Map.of(
            "John", "Snow",
            "Aria", "Stark",
            "Tyrion", "Lannister",
            "Daenerys", "Targaryen"
    );

    private static void printAlive(String surname) {
        System.out.println(surname + " is alive");
    }

    private static void printDead() {
        System.out.println("One more dead");
    }

    private static Optional<String> getSurname(String name) {
        return Optional.ofNullable(survivors.get(name));
    }
    //--------------------------------------------------------------------------------------------------------------
    private static String findWorkAddress(String username) {
        return Math.random() > 0.2 ? username + "'s Work" : null;
    }

    private static Optional<String> findHomeAddress(String username) {
        return Math.random() > 0.5 ? Optional.of(username + "'s Home") : Optional.empty();
    }
    //--------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
    // java 8
        List<String> names1 = List.of("John", "Aria", "Tyrion", "Daenerys", "Eddard");
        names1.stream()
                .map(ExampleOptional9::getSurname)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(System.out::println);
    // java 9
        List<String> names2 = List.of("John", "Aria", "Tyrion", "Daenerys", "Eddard");
        names2.stream()
                .map(ExampleOptional9::getSurname)
                .flatMap(Optional::stream)
                .forEach(System.out::println); //Snow Stark Targaryen
//------------------------------------------------------------------------------------------------------------------
        //Optional.ifPresentOrElse()
        //Еще один полезный метод, который позволяет выполнить одно действие, если значение в Optional присутствует,
        // и другое  - если его нет.
        List<String> names = List.of("John", "Aria", "Tyrion", "Daenerys", "Eddard");
        names.stream()
                .map(ExampleOptional9::getSurname)
                .forEach(s -> s.ifPresentOrElse(ExampleOptional9::printAlive, ExampleOptional9::printDead));
//------------------------------------------------------------------------------------------------------------------
        String username = "John";
        String homeAddress = findHomeAddress(username)
                .orElseGet(() -> findWorkAddress(username));
        System.out.println(homeAddress);

    }
}
