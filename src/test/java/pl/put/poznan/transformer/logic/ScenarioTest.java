package pl.put.poznan.transformer.logic;

import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class ScenarioTest {

    private Scenario scenario;

    @Before
    public void setUp() {
        scenario = new Scenario("src/test/resources/data.txt");
    }

    @Test
    public void toStringTest() {
    }

    @Test
    public void getTitleTest() {
        assertEquals("Scenariusz", scenario.getTitle());
    }

    @Test
    public void getActorsTest() {
        List<String> ret = scenario.getActors();

        assertEquals(2, ret.size());
        assertEquals("BIBLIOTEKARZ", ret.get(0));
        assertEquals("SYSTEM", ret.get(1));
    }

    @Test
    public void getStepsTest() {
    }

    @Test
    public void getStepsCountTest() {
        assertEquals(13, scenario.getStepsCount());
    }

    @Test
    public void getConditionalDecisionCountTest() {
        assertEquals(2, scenario.getConditionalDecisionCount());
    }

    @Test
    public void getBuggableLinesTest() {
        List<String> ret= scenario.getBuggableLines();

        assertEquals(1, ret.size());
        assertEquals("Wyświetla się formularz.\n", ret.get(0));
    }
}
