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


}