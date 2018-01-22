package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.TextTransformer;

import java.util.Arrays;


@RestController
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);



    @RequestMapping(value = "/tolevel")
    public String getScenarioToLevel(@RequestParam(value="scenario") String scenario, @RequestParam(value="depth") int depth) {
        Scenario s = new Scenario(scenario);
        return s.getSubScenarios(s.toString().split("\n"), depth);
    }

    @RequestMapping(value = "/stepscount")
    public String getStepsCount(@RequestParam(value="scenario") String scenario) {
        Scenario s = new Scenario(scenario);
        return Integer.toString(s.getStepsCount());
    }

    @RequestMapping(value = "/numsteps")
    public String getNumberedSteps(@RequestParam(value="scenario") String scenario) {
        Scenario s = new Scenario(scenario);
        return s.toNumberedString();
    }

    @RequestMapping(value = "/keywordscount")
    public String getCountWithKeywords(@RequestParam(value="scenario") String scenario) {
        Scenario s = new Scenario(scenario);
        return Integer.toString(s.getConditionalDecisionCount());
    }

    @RequestMapping(value = "/stepswithoutactor")
    public String getStepsWithoutActor(@RequestParam(value="scenario") String scenario) {
        Scenario s = new Scenario(scenario);
        String res = String.join(",", s.getBuggableLines());
        return res;
    }
}


