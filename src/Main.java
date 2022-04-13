import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        long young = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(young);
        Stream<String> conscript = persons.stream()
                .filter(person -> person.getAge() > 18 && person.getAge() < 27)
                .filter(person -> person.getSex() == Sex.MAN)
                .map(Person::getFamily);
        System.out.println(conscript.collect(Collectors.toList()));
        Stream<Person> workableWoman = persons.stream()
                .filter(person -> person.getAge() > 18 && person.getAge() < 60)
                .filter(person -> person.getSex() == Sex.WOMAN)
                .filter(person -> person.getEducation() == Education.HIGHER)
                .sorted((Comparator.comparing(Person::getFamily)));
        System.out.println(workableWoman.collect(Collectors.toList()));
        Stream<Person> workableMan = persons.stream()
                .filter(person -> person.getAge() > 18 && person.getAge() < 65)
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily));
        System.out.println(workableMan.collect(Collectors.toList()));
    }
}
