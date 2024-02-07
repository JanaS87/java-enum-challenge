package org.example;

import java.util.*;

public class PersonRepository {
    List<Person> persons;

    public PersonRepository() {
        persons = new ArrayList<>();
    }

    public Optional<Person> findPersonById(int id) {
        for (Person person : persons) {
            if (person.personId() == id) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
//        return persons.stream().filter(person -> person.personId() == id).findFirst();
    }

    public void countPersonsByGender() {
        Map<Gender, Integer> countByGender = new HashMap<>();

        for (Person person : persons) {
            Gender gender = person.gender();
            countByGender.put(gender, countByGender.getOrDefault(gender, 0) + 1);
        }

        for (Map.Entry<Gender, Integer> entry : countByGender.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public Optional<Person> findPersonByName(String name) {
        for (Person person : persons) {
            if (person.name().equals(name)) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    public List<String> findPersonByFavoriteDay(DaysOfWeek day) {
        List<String> personsByDay = new ArrayList<>();
        for (Person person : persons) {
            if (person.favoriteDay().equals(day)) {
                personsByDay.add(person.name());
            }
        }
        return personsByDay;
    }
}
