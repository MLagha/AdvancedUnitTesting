package com.sparta.ml;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

//Hamcrest is another way of writing assertions. It is a dependency used for testing.
public class HamcrestTests {
    private Spartan spartan;
    @BeforeEach
    void setup() {
        spartan = new Spartan(1, "David", "Data", LocalDate.of(2022, 12, 12));
    }

    @Test
    @DisplayName("Checl Spartan is called Manish")
    void checkSpartanIsCalledManish() {
        assertThat(spartan.getName(), equalTo("Manish"));
    }

    @Test
    @DisplayName("Check that spartan has an id field")
    void checkThatSpartanHasAnIdField() {
        assertThat(spartan, hasProperty("id", equalTo(1)));
    }

    @Test
    @DisplayName("Hamcrest String methods")
    void hamcrestStringMethods() {
        assertThat(spartan.getName(), startsWith("Ma"));
    }


}
