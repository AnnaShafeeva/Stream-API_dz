import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;

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

        long countBabe = persons.stream()
                .filter(personBaby -> personBaby.getAge() < 18)
                .count();


        List<String> mansArmy = persons.stream()
                .filter(personArmy -> personArmy.getAge() >= 18 && personArmy.getAge() <= 27
                        && personArmy.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());


        List<Person> peopleWork = persons.stream()
                .filter(personWork -> personWork.getEducation() == Education.HIGHER)
                .filter(personWork -> personWork.getAge() >= 18)
                .filter(personWork -> (personWork.getAge() <= 60 && personWork.getSex() == Sex.WOMAN)
                        || (personWork.getAge() <= 65 && personWork.getSex() == Sex.MAN))
                .sorted(comparing(Person::getFamily))
                .collect(Collectors.toList());

    }
}
