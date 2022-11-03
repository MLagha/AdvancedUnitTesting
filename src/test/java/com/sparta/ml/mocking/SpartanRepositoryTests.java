package com.sparta.ml.mocking;

import com.sparta.ml.Spartan;
import com.sparta.ml.SpartanRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.fail;

//Creating a fake Spartan object. First add Mockito dependency
//testing the methods within Spartan through a fake Spartan object
public class SpartanRepositoryTests {
    private Spartan mockSpartan;
    private Spartan spartan;
    private Spartan spySpartan;
    
    @BeforeEach
    void init() {
        mockSpartan = Mockito.mock(Spartan.class);

        spartan = new Spartan(1, "Manish", "Java", LocalDate.now());
        spySpartan = Mockito.spy(spartan); //Partial Mock - to only fake parts and not all
    }

    //1- Creating a fake Spartan object
    @Test
    @DisplayName("Testing Spartan Repository Print Method")
    void testingSpartanRepositoryPrintMethod() {
        SpartanRepository.addSpartan(mockSpartan);                                      //Creating a fake Spartan object

        Mockito.when(mockSpartan.toString()).thenReturn("Found Mock Spartan");        //When someone Calls mockSpartan.toString Return "Found Mock Spartan" (for Mock)
        //Mockito.doReturn("Found Mock Spartan").when(mockSpartan.toString());          //Another way of writing it (for Spies)

        Mockito.doThrow(NullPointerException.class).when(mockSpartan).setId(Mockito.anyInt());      //if setId is called with any int then throw the exception

        //System.out.println(SpartanRepository.getAllSpartans());
        Assertions.assertEquals("Found Mock Spartan" + "\n", SpartanRepository.getAllSpartans());
    }

    @Test
    @DisplayName("Testing Method Order")
    void testingMethodOrder() {
        Mockito.when(mockSpartan.getStartDate())
                .thenReturn(LocalDate.now())     //return
                .thenReturn(LocalDate.MAX);     //return if called a second time

        System.out.println(mockSpartan.getStartDate());
        System.out.println(mockSpartan.getStartDate());
    }

    //2- Use Verify: ONLY for mocking to check if the object is used
    //Use Assertions: When real code is working - to check if the object is used
    @Test
    @DisplayName("Check that the getId method is called on our mock")
    void checkThatTheGetIdMethodIsCalledOnOurMock() {
        SpartanRepository.addSpartan(mockSpartan);
        boolean present = SpartanRepository.findSpartan(1).isPresent();
        Mockito.verify(mockSpartan, Mockito.times(1)).getId();        //Verify that the getId method has been called once
    }

    //3- Verify the order of the objects
    @Test
    @DisplayName("Check that spartan metiods are called in the right order")
    void checkThatSpartanMethodsAreCalledInTheRightOrder() {
        mockSpartan.setName("Manish");
        mockSpartan.setCourse("C#");
        System.out.println(mockSpartan.getName() + " " + mockSpartan.getCourse());

        InOrder inOrder = Mockito.inOrder(mockSpartan);
        inOrder.verify(mockSpartan).setName("Manish");
        inOrder.verify(mockSpartan).setCourse("C#");
        inOrder.verify(mockSpartan).getName();
        inOrder.verify(mockSpartan).getCourse();
    }
}
