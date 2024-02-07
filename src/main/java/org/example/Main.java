package org.example;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person(1, "John", DaysOfWeek.MONDAY, Gender.MALE);
        Person person2 = new Person(2, "Jane", DaysOfWeek.SATURDAY, Gender.FEMALE);
        Person person3 = new Person(3, "Hans", DaysOfWeek.MONDAY, Gender.DIVERSE);

        PersonRepository repository = new PersonRepository();

        repository.persons.add(person1);
        repository.persons.add(person2);
        repository.persons.add(person3);

        Optional<Person> person = repository.findPersonById(1);
        person.ifPresent(value -> System.out.println("Person found: " + value.name() + " " + "favorite Day: " +
                checkDay(value.favoriteDay())));

        person = repository.findPersonById(2);
        person.ifPresent(value -> System.out.println("Person found: " + value.name() + " " + "favorite Day: " +
                checkDay(value.favoriteDay())));

        person = repository.findPersonById(3);
        person.ifPresentOrElse(value -> System.out.println("Person found: " + value.name() + " " + "favorite Day: " +
                checkDay(value.favoriteDay())), () -> System.out.println("Person not found"));

        System.out.println(" ");

        System.out.println("Persons counted by gender");

        repository.countPersonsByGender();

        System.out.println(" ");

        System.out.println("Persons found by name");
        // positive case
        Optional<Person> personByName = repository.findPersonByName("John");
        personByName.ifPresentOrElse(value -> System.out.println("There is a person named" + " " + value.name()) , () -> System.out.println("Person not found"));
        // negative case
        Optional<Person> personByName2 = repository.findPersonByName("Fritz");
        personByName2.ifPresentOrElse(value -> System.out.println("There is a person named" + " " + value.name()) , () -> System.out.println("Person not found"));

        System.out.println(" ");
        System.out.println("Persons found by favorite day");
        List<String> personsByDay = repository.findPersonByFavoriteDay(DaysOfWeek.MONDAY);
        System.out.println("Persons found by favorite day: " + personsByDay);
    }

    public static String checkDay(DaysOfWeek day) {
        return switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> day.toString();
            default -> "Weekend";
        };
    }
}