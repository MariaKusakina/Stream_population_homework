package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        Long lowAge = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        List<String> soldier = persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        List<Person> sortedWorker = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(Main::isWorker)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
//        System.out.println(lowAge);
//        soldier.stream().limit(10).forEach(System.out::println);
//        sortedWorker.stream().limit(10).forEach(System.out::println);



    }
    private static boolean isWorker (Person person) {
        if((person.getSex() == Sex.WOMAN && person.getAge() >= 18 && person.getAge() <= 60)
                || (person.getSex() == Sex.MAN && person.getAge() >= 18 && person.getAge() <= 65)) {
           return true;
        } else {
            return false;
        }
    }
}
