package pl.put.poznan.transformer.logic;

import java.io.*;
import java.util.ArrayList;


public class Scenario
{
    Presenter presenter;
    private String title;
    private ArrayList<String> actors;
    private ArrayList<Step> steps;
    final private ArrayList<String> keywords = new ArrayList<String>(){{
        add("IF");
        add("ELSE");
        add("FOR EACH");
    }};


    /**
     * Wczytuje scenariusz z pliku tekstowego
     *
     * @param fileName nazwa pliku z ktorego ma zostac wczytany scenariusz
     */
    public Scenario(String fileName)
    {
        actors = new ArrayList<String>();
        steps = new ArrayList<Step>();
        presenter = new Presenter();

        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            int n = 0;

            while ((line = bufferedReader.readLine()) != null) {
                if (n == 0)
                    title = line;
                else if (n == 1) {
                    for (String actor : line.split(" "))
                        actors.add(actor.toUpperCase());
                } else {
                    Step step = new Step(line + "\n");
                    steps.add(step);
                }

                n += 1;
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex)
        {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

    /**
     * Zwraca cały scenariusz
     *
     */
    public String toString()
    {
        String result = "";

        result += title + "\n";

        for (String a : actors)
            result += a + " ";
        result += "\n";

        result += getSteps();

        return result;
    }

    /**
     * Zwraca tytuł scenariusza
     *
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Zwraca aktorów scenariusza
     *
     */
    public ArrayList<String> getActors()
    {
        return actors;
    }

    /**
     * Zwraca kroki scenariusza
     *
     */
    public String getSteps()
    {
        String result = "";

        for (Visitable step : steps)
            result += step.accept(presenter);

        return result;
    }

    /**
     * Zwraca liczbę kroków scenariusza
     *
     */
    public int getStepsCount()
    {
        return steps.size() == 0 ? 0 : steps.size();
    }

    /**
     * Sprawdza czy ciąg znaków zawiera słowa kluczowe
     *
     * @param line rozpatrywany ciąg znaków
     */
    private boolean containsKeyword(String line)
    {
        for (String keyword : keywords)
        {
            if (line.contains(keyword))
                return true;
        }

        return false;
    }

    /**
     * Zwraca liczbę kroków zawierających słowo kluczowe
     *
     */
    public int getConditionalDecisionCount()
    {
        int count = 0;

        for (Step step : steps)
        {
            if (containsKeyword(step.getLine()))
                count++;
        }

        return count;
    }

    /**
     * Zwraca przefiltrowany ciąg znaków - kasuje wszystkie znaki tabulacji, cyfry i kropki
     *
     * @param line rozpatrywany ciąg znaków
     */
    private String filterLine(String line)
    {
        line = line.replaceAll("^[\\t0-9\\.]+", "");

        for (String keyword : keywords)
            line = line.replaceFirst(keyword + " ", "");

        return line;
    }

    /**
     * Sprawdza czy ciąg znaków rozpoczyna się od aktora
     *
     * @param line rozpatrywany ciąg znaków
     */
    private boolean startsWithActor(String line)
    {
        for (String actor : actors)
        {
            if (line.length() >= actor.length() &&  actor.equals(line.substring(0, actor.length()).toUpperCase()))
                return true;
        }

        return false;
    }

    /**
     * Zwraca kroki, które nie zaczynają się od aktora
     *
     */
    public ArrayList<String> getBuggableLines()
    {
        ArrayList<String> result = new ArrayList<String>();

        for (Step step : steps)
        {
            if (!containsKeyword(step.getLine()) && !startsWithActor(filterLine(step.getLine())))
                result.add(filterLine(step.getLine()));
        }

        return result;
    }

    /**
     * Zwraca ponumerowane kroki scenariusza
     *
     */
    public String toNumberedString()
    {
        int max = steps.size();
        int[] tab = new int[max];

        for (int i = 0; i < max; i++)
            tab[i] = 0;

        String result = "";

        result += title + "\n";
        for (String a : actors)
            result += a + " ";
        result += "\n";

        for (Step step : steps)
        {
            int p = 0;
            String numbers = "";

            while((step.getLine().charAt(p))== '\t')
                p++;

            tab[p]++;

            for (int i = p + 1; i < max; i++)
                tab[i] = 0;

            StringBuilder bufferedText = new StringBuilder(step.getLine());
            for(int i = 0; i < p + 1; i++)
                numbers = numbers + tab[i] + ".";

            bufferedText.insert(p,numbers);

            result += bufferedText.toString();
        }

        return result;
    }

    public String getSubScenarios(String[] steps, int depth)
    {
        StringBuilder ret = new StringBuilder();

        for (String i : steps)
        {
            int count = 0;

            while (i.charAt(count) == '\t')
                ++count;

            if (count < depth) {
                ret.append(i);
                ret.append("\n");
            }
        }

        return ret.toString();
    }

    public int getMaxDepth()
    {
        int max = 0;

        for (Step step : steps) {
            int count = 0;

            while (step.getLine().charAt(count) == '\t')
                ++count;

            max = Math.max(max, count);
        }

        return max;
    }

}
