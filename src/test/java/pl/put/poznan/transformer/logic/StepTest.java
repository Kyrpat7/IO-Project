package pl.put.poznan.transformer.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StepTest {

    Step s;
    Visitor v;

    @Before
    public void setUp() throws Exception {
        s = new Step("Test line");
        v = mock(Visitor.class);
        when(v.visit(s)).thenReturn("Ultra test line");
    }

    @Test
    public void getLine() {
        assertEquals("Test line", s.getLine());
    }

    @Test
    public void accept() {
        assertEquals("Ultra test line", s.accept(v));
    }
}