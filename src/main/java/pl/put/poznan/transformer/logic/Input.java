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

    public String getTitle()
    {
        return title;
    }

    public ArrayList<String> getActors()
    {
        return actors;
    }

    public String getSteps()
    {
        return steps;
    }

    public int getStepsCount()
    {
        return steps == "" ? 0 : steps.split("\n").length;
    }

    private boolean containsKeyword(String line)
    {
        for (String keyword : keywords)
        {
            if (line.contains(keyword))
                return true;
        }

        return false;
    }

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

    private String filterLine(String line)
    {
        line = line.replaceAll("^[\\t0-9\\.]+", "");

        for (String keyword : keywords)
            line = line.replaceFirst(keyword + " ", "");

        return line;
    }

    private boolean startsWithActor(String line)
    {
        for (String actor : actors)
        {
            if (line.length() >= actor.length() &&  actor.equals(line.substring(0, actor.length()).toUpperCase()))
                return true;
        }

        return false;
    }

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
}
//git test