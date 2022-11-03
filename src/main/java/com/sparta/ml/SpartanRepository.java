package com.sparta.ml;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

//It's hard to test this class because there's a level of dependency with another class (Spartan). -->> thus we have mocking
public class SpartanRepository {
    private static final ArrayList<Spartan> spartans = new ArrayList<>();
    public static void addSpartan(Spartan spartan) {
        spartans.add(spartan);
    }

    //Optional is a container object used to contain not-null object. It is used to represent null with absent value
            //Always as a tester try to ask developers to not use optionals, so that you have things to test against
    public static Optional<Spartan> findSpartan(int id) {
        Spartan returnedSpartan = null;
        for (Spartan spartan : spartans) {
            if (spartan.getId() == id) {
                returnedSpartan = spartan;
            }
        }
        return Optional.ofNullable(returnedSpartan);
    }
    public static String getAllSpartans() {
        StringBuilder SpartansInArray = new StringBuilder();
        for (Spartan spartan : spartans) {
            SpartansInArray.append(spartan.toString()).append("\n");
        }
        return SpartansInArray.toString();
    }
    public static boolean removeSpartan(int id) {
        return spartans.removeIf(spartan -> spartan.getId() == id);
    }

    public static void main(String[] args) {
        addSpartan(new Spartan(1, "Manish", "Java", LocalDate.of(2022, 10, 10)));
        addSpartan(new Spartan(2, "Stephen", "Java", LocalDate.of(2022, 10, 10)));
        addSpartan(new Spartan(4, "David", "Java", LocalDate.of(2022, 10, 10)));
        addSpartan(new Spartan(5, "Danny", "Java", LocalDate.of(2022, 10, 10)));
        System.out.println(findSpartan(5));
        //System.out.println(findSpartan(6));           //try this to see what happen when it's a Null
        //System.out.println(findSpartan(5).get().getName());

        //findSpartan(6).isPresent();
    }
}