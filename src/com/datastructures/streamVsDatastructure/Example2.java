package com.datastructures.streamVsDatastructure;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//Пожалуйста, заполните следующий фрагмент, чтобы создать карту, которая отображает имя перечисления
// для редактирования привилегий.
enum UserType {
    ADMIN(true), MODERATOR(true), DEVELOPER(false);

    private final boolean editPermission;

    UserType(boolean editPermission) {
        this.editPermission = editPermission;
    }
    public boolean hasEditPermission() {
        return editPermission;
    }
}
public class Example2 {
    Map<String, Boolean> userTypeToEditPermission() {
        return Stream.of(UserType.values())
			.collect(Collectors.toMap(Enum::name, UserType::hasEditPermission));
    }
    public static void main(String[] args) {
        Example2 example2 = new Example2();
        example2.userTypeToEditPermission().values().forEach(System.out::println);

    }
}
