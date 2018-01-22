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
        when(my_step.getLine()).thenReturn("Test step");
    }

    @Test
    public void visit() {
        assertEquals("Test step", p.visit(my_step));
    }
}