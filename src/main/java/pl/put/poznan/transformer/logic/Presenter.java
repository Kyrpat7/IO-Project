package pl.put.poznan.transformer.logic;

public class Presenter implements Visitor
{
    @Override
    public String visit(Step step)
    {
        return step.getLine();
    }
}
