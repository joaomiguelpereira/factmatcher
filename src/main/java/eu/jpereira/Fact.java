package eu.jpereira;


/**
 * Model for a fact. A fact must have a fact name.
 */
public class Fact implements Comparable<Fact> {

    private final String factName;

    public Fact(String factName) {
        if (factName == null || factName.isEmpty()) {
            throw new IllegalArgumentException("A fact name cannot be null or empty");
        }

        this.factName = factName;
    }

    /**
     * Get the fact name. This is the name know to "outside"
     *
     * @return the name of the fac
     */
    public String getFactName() {
        return factName;
    }


    @Override
    public int compareTo(Fact other) {
        return this.factName.compareTo(other.getFactName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Fact fact = (Fact) o;

        return factName.equals(fact.factName);
    }

    @Override
    public int hashCode() {
        return factName.hashCode();
    }

    @Override
    public String toString() {
        return "Fact{" +
                "factName='" + factName + '\'' +
                '}';
    }
}
