package eu.jpereira;

import java.util.Iterator;
import java.util.Set;

/**
 * Model a match result.
 */
public class MatchResult {


    /**
     * The set of facts in this result
     */
    private final Set<Fact> facts;
    private final Set<Character> nextCharacters;

    public MatchResult(Set<Fact> facts,Set<Character> nextCharacters ) {
        if ( facts == null || nextCharacters == null ) {
            throw new IllegalArgumentException("Both Fact Set and Charctaer Set must not be null");
        }
        this.nextCharacters = nextCharacters;
        this.facts = facts;
    }

    /**
     * Get the iterator to iterate through matched results
     * @return An iterator for Fact
     */
    public Iterator<Fact> getFactIterator() {
        return this.facts.iterator();
    }


    /**
     * Get the iterator to iterate through the next characters
     * @return An iterator for Character
     */

    public Iterator<Character> getNextCharacterIterator() {
        return this.nextCharacters.iterator();
    }


}
