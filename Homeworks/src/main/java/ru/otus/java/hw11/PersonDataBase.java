package ru.otus.java.hw11;

import java.util.HashMap;
import java.util.HashSet;

public class PersonDataBase {

    public static HashMap<Long, Person> personHashMap = new HashMap<>();
    public static HashSet<Position> positionHashSet = new HashSet<>();

    public PersonDataBase() {
        positionHashSet.add(Position.MANAGER);
        positionHashSet.add(Position.DIRECTOR);
        positionHashSet.add(Position.BRANCH_DIRECTOR);
        positionHashSet.add(Position.SENIOR_MANAGER);
    }

    public Person findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        return personHashMap.get(id);
    }

    public void add(Person person) {
        if (person == null || person.getId() == null) {
            throw new IllegalArgumentException("Person or Id cannot be null");
        }
        personHashMap.put(person.getId(), person);
    }

    public boolean isManager(Person person) {
        return positionHashSet.contains(person.getPosition());
    }

    public boolean isEmployee(Long id) {
        return !positionHashSet.contains(findById(id).getPosition());
    }


}
