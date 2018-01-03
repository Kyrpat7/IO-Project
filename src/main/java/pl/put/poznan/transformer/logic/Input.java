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
            //step = step.replaceAll(",","");

            result = result + step;
        }
        return result;
    }


}
//git test 3