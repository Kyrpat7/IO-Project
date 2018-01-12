package pl.put.poznan.transformer.logic;


/**
 * Interface elementu odwiedzanego
 */
public interface Visitable
{
    /**
     * Akceptuje wizytatora
     * @param visitor - wizytator, który chce odwiedzić element
     */
    public String accept(Visitor visitor);
}
