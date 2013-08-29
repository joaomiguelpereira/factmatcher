package eu.jpereira;

import java.util.Collections;
import java.util.TreeSet;

/**
 * Testing implementation to feed dummy facts
 */
public class TestFactReader extends TreeSet<String> implements FactReader {

    public TestFactReader(String... facts) {
        if ( facts.length == 0 ) {
            initializeWithDummyFacts();
        }
        Collections.addAll(this,facts);
    }

    private void initializeWithDummyFacts() {
        this.add("Aveiro");
        this.add("Amarante");

        this.add("Viseu");
        this.add("Mealhada");
        this.add("Oliveira do Hospital");
        this.add("Ovar");
        this.add("Paredes");
        this.add("Porto");
        this.add("Lisboa");
    }


}
