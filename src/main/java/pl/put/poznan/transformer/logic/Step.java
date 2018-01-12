package pl.put.poznan.transformer.logic;

import java.util.ArrayList;

public class Step implements Visitable
{
    private String line;

    public Step(String line)
    {
        this.line = line;
    }

    public String getLine()
    {
        return line;
    }

    @Override
    public String accept(Visitor visitor)
    {
        return visitor.visit(this);
    }
}
