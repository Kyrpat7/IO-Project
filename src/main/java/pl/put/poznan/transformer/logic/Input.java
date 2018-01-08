package pl.put.poznan.transformer.logic;

import java.io.*;
import java.util.ArrayList;


public class Input
{
    private String title;
    private ArrayList<String> actors;
    private String steps;
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
    public Input(String fileName)
    {
        actors = new ArrayList<String>();
        steps = "";

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
                } else
                    steps += line + "\n";

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
        result += steps;

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
        return steps;
    }

    /**
     * Zwraca liczbę kroków scenariusza
     *
     */
    public int getStepsCount()
    {
        return steps == "" ? 0 : steps.split("\n").length;
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

        for (String step : steps.split("\n"))
        {
            if (containsKeyword(step.toUpperCase()))
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

        for (String step : steps.split("\n"))
        {
            if (!startsWithActor(filterLine(step)))
                result.add(step);
        }

        return result;
    }

    /**
     * Zwraca ponumerowane kroki scenariusza
     *
     */
    public String getNumberedSteps()
    {
        int max = steps.split("\n").length;
        int[] tab = new int[max];
        for (int i = 0; i < max; i++)
            tab[i] = 0;
        String result = "";

        result += title + "\n";
        for (String a : actors)
            result += a + " ";
        result += "\n";

        for (String step : steps.split("\n"))
        {
            int p = 0;
            String numbers = "";
            while((step.charAt(p))== '\t')
            {
                p++;
            }
            tab[p]++;
            for (int i = p + 1; i < max; i++)
                tab[i] = 0;
            StringBuilder bufferedText = new StringBuilder(step);
            for(int i = 0; i < p + 1; i++)
            {
                numbers = numbers + tab[i] + ".";
            }
            bufferedText.insert(p,numbers);
            step = bufferedText.substring(0)+"\n";

            result = result + step;
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
        String[] arr = steps.split("\n");

        int max = 0;

        for (String i : arr) {
            int count = 0;

            while (i.charAt(count) == '\t')
                ++count;

            max = Math.max(max, count);
        }

        return max;
    }

}
