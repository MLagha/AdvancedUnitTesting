package com.sparta.ml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParameterizedTests {

    @ParameterizedTest(name = "{index} of {argumentsWithNames}")
    @ValueSource(ints = {1, 20, 30, 5, 7, 15})
    @DisplayName("Check for int higher than 10")
    void checkForIntHigherThan10(int num){
        assertTrue(num > 10);
    }

    @ParameterizedTest()
    //@CsvSource({"David", "Manish"})       //or import them from a CSV file as below... Or use a sourceMethod as below
    @CsvFileSource(resources = "/names.csv")
    //@NullAndEmptySource                   //checks against Null and Empty
    @DisplayName("Using CSV source for test")
    void runCSVTests(String name) {
        assertEquals(5, name.length());
    }

    @ParameterizedTest
    @MethodSource("sourceMethod")           //The most used test: method source - very fixable
    @DisplayName("Using a method source")
    void usingAMethodSource(int number, String name) {
        assertEquals(name.length(), number);
    }
    public static Stream<Arguments> sourceMethod() {
        return Stream.of(
                Arguments.of(6, "Manish"),
                Arguments.of(5, "David"),
                Arguments.of(7, "Stephen"),
                Arguments.of(5, "Danny")
        );
    }

    //Testing if it throws an exception
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 5, 7, 8, 10})
    @DisplayName("Testing for exceptions")
    void testingForExceptions(int number) {
        ArrayList<Integer> numberList = new ArrayList<>(Arrays.asList(1, 2, 3));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> numberList.get(number));     //test will pass if Exception is thrown
        //Assertions.assertDoesNotThrow(() -> numberList.get(number));                              //test will pass if Exception is NOT thrown
    }
}
