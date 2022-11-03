package com.sparta.ml;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class SpartanTests {
    //Hooks - Block of repeated code
            //Hooks Names: BeforeAll (static method), BeforeEach (instant method), AfterEach (instant method), AfterAll (static method)

    private Spartan spartan;

    @BeforeAll
    static void initAll(TestInfo testInfo) {
        System.out.println(testInfo.getTestClass() + "has started");
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        spartan = new Spartan(1, "Manish", "Java", LocalDate.of(2022, 12,12));
        System.out.println(testInfo.getDisplayName() + " executing");
    }

    @Test
    @Disabled ("Waiting for Manish to finish his method")             //Disable this test - the comment is to tell why this test is disabled
    //@DisabledIf(value = "checkForSpartan", disabledReason = "Code has not been completed yet")
    @DisplayName("Check that the Spartan is called Manish")
    void checkThatTheSpartanIsCalledManish() {
        assertEquals("Manish", spartan.getName());
    }
/*
    boolean checkForSpartan() {
        return true;
    }
 */


    @Tag("valueCheck")      //to run anything that includes the tag... or you can do exclude the tag. You need to first add plugins
    @RepeatedTest(
            value = 6,      //test repeated for 6 times
            name = RepeatedTest.LONG_DISPLAY_NAME)      //if you want to repeat writing the DisplayName each time.
            //name = "{displayName} is running test {currentRepetition}")       //have your own display message
    @DisplayName("Check that the id is a positive number")
    void checkThatTheIdIsAPositiveNumber() {
        //assumeTrue(spartan.getId() == 12);        //if assumption pass, then continue to assertions and run the test
        assertTrue(spartan.getId() >= 0);
        //assertEquals(10, spartan.getId());
    }

    @Nested     //use it to break up and organise your test
    @DisplayName("Nested Tests")
    class NestedTests {
        @Test
        @DisplayName("Nested examples")
        void nestedExamples() {
            assertEquals(1, 1);
        }

        @Test
        @DisplayName("Another Nested test")
        void anotherNestedTest() {
            assertEquals(1, 1);
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName() + " completed");
    }
}
    //////////////////////Task: writ tests for Spartan class

    @ParameterizedTest
    @DisplayName("Testing for IDs less than 10")
    @ValueSource(ints = {11, 19, 34, 992, 999, 40, 654})
    void testingForIdsLessThan10(int id){
        assertTrue(id > 10 && id < 1000);
    }

    @Test
    @DisplayName("Check if the date joined")
    void checkIfTheDateJoined() {
        assertTrue(spartan.getStartDate().isAfter(LocalDate.now()));
    }

    @ParameterizedTest()
    @CsvFileSource(resources = "/courses.csv")
    @DisplayName("Using CSV source for test")
    void checkIfCourseIsOneOfTheFollowing(String course) {
        Assertions.assertTrue(course.contains("Java"));
    }

    @AfterAll
    static void tearDownAll(TestInfo testInfo) {
        System.out.println(testInfo.getTestClass() + " has finished");
    }
}