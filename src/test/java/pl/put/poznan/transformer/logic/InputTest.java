package pl.put.poznan.transformer.logic;

import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class InputTest {

    private Input input;

    @Before
    public void setUp() {
        input = new Input("src/test/resources/data.txt");
    }

    @Test
    public void toStringTest() {
    }

    @Test
    public void getTitleTest() {
        assertEquals("Scenariusz", input.getTitle());
    }

    @Test
    public void getActorsTest() {
        List<String> ret = input.getActors();

        assertEquals(2, ret.size());
        assertEquals("BIBLIOTEKARZ", ret.get(0));
        assertEquals("SYSTEM", ret.get(1));
    }

    @Test
    public void getStepsTest() {
    }

    @Test
    public void getStepsCountTest() {
        assertEquals(13, input.getStepsCount());
    }

    @Test
    public void getConditionalDecisionCountTest() {
        assertEquals(2, input.getConditionalDecisionCount());
    }

    @Test
    public void getBuggableLinesTest() {
        List<String> ret= input.getBuggableLines();

        assertEquals(2, ret.size());
        assertEquals("Wyświetla się formularz.", ret.get(0));
        assertEquals("\tFOR EACH egzemplarz:", ret.get(1));
    }
}