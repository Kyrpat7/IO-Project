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
    }

    @Test
    public void getLine() {
        assertEquals("Test line", s.getLine());
    }

    @Test
    public void accept_test1() {
        when(v.visit(s)).thenReturn("Ultra test line");
        assertEquals("Ultra test line", s.accept(v));
    }

    @Test
    public void accept_test2() {
        when(v.visit(s)).thenReturn("wololololololololo\nwowdowdoowdkwodkowdowdk");
        assertEquals("wololololololololo\nwowdowdoowdkwodkowdowdk", s.accept(v));
    }

    @Test
    public void accept_test3() {
        when(v.visit(s)).thenReturn("       ew0wr9i0wr9\t\n\ti\n 0wir9 w      0wir 0wir 0wi\te0i9ewr0ir0iewr0i9ewr0ew");
        assertEquals("       ew0wr9i0wr9\t\n\ti\n 0wir9 w      0wir 0wir 0wi\te0i9ewr0ir0iewr0i9ewr0ew", s.accept(v));
    }
}