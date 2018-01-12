package pl.put.poznan.transformer.logic;

/**
 * Interface Visitor
 */
public interface Visitor
{
    /**
     * Odwiedza element
     * @param step - element do odwiedzenia
     */
    public String visit(Step step);
}
