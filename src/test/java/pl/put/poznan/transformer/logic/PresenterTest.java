package pl.put.poznan.transformer.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class PresenterTest {
    private Step my_step;
    private Presenter p;

    @Before
    public void setup() {
        p = new Presenter();

        my_step = mock(Step.class);

    }

    @Test
    public void visit_test1() {
        when(my_step.getLine()).thenReturn("Test step");
        assertEquals("Test step", p.visit(my_step));
    }

    @Test
    public void visit_test2() {
        when(my_step.getLine()).thenReturn("Ultra super test line");
        assertEquals("Ultra super test line", p.visit(my_step));
    }
}