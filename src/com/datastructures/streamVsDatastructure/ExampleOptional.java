package com.datastructures.streamVsDatastructure;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class  Person {
    private int id;
    private String firstName;
    private String lastName;
    private String bill;

    public Person(int id, String firstName, String lastName, String bill) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bill = bill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bill=" + bill +
                '}';
    }


//Optional.of — возвращает Optional-объект.
//Optional.ofNullable -возвращает Optional-объект, а если нет дженерик-объекта, возвращает пустой Optional-объект.
//Optional.empty — возвращает пустой Optional-объект.
//Существует так же два метода, вытекающие из познания, существует обёрнутый объект или нет — isPresent() и ifPresent();
//Как получить объект, содержащийся в Optional?
//
//Существует три прямых метода дальнейшего получения объекта семейства orElse(); Как следует из перевода, эти методы
// срабатывают в том случае, если объекта в полученном Optional не нашлось.
//orElse() — возвращает объект по дефолту.
//orElseGet() — вызывает указанный метод.
//orElseThrow() — выбрасывает исключение.
//
//Работа с полученным объектом.
//Как я писал выше, у Optional имеется неплохой инструментарий преобразования полученного объекта, а именно:
//get() — возвращает объект, если он есть.
//map() — преобразовывает объект в другой объект.
//filter() — фильтрует содержащиеся объекты по предикату.
//flatmap() — возвращает множество в виде стрима.
//
}
public class ExampleOptional{
    public static void main(String[] args) {

        List<Person> listPerson = List.of(
                new Person(1,"Jania","Van","23423,34"),
                new Person(2,"Din","Kent","3423,53"),
                new Person(3, "Gen", "Rambo","2342,33"));


        List<Optional<Person>> persons = listPerson
                .stream()
                .map(Optional::ofNullable)                                                                                                                                                                                                                    // .map(number -> Integer.parseInt(number))
                .collect(Collectors.toList());

        persons.forEach(System.out::println);
        //----------------------------------------------------------------------------------------------------
        List<String> rawNumbers = Arrays.asList("1", null, "3", "2", "1", null);

        List<Integer> numbers = rawNumbers
                .stream()
                .map(Optional::ofNullable)
                .map(ExampleOptional::parseToInt)
                .collect(Collectors.toList());
        numbers.forEach(System.out::println);
        assert !rawNumbers.equals(numbers);
        assert numbers.equals(Arrays.asList(1, 0, 3, 2, 1, 0));

    }

        public static Integer parseToInt(Optional<String> opt) {
            return opt.map(Integer::parseInt).orElse(0);
            //
    }


    }

