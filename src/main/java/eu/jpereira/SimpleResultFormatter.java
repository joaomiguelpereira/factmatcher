package eu.jpereira;

import eu.jpereira.cli.ResultFormatter;

import java.util.Iterator;

/**
 * A simple implementation for the a Formatter
 */
public class SimpleResultFormatter implements ResultFormatter {

    @Override
    public String format(MatchResult result) {
        if ( result == null ) {
            throw new IllegalArgumentException("Result argument must not be null");
        }
        StringBuilder builder = new StringBuilder();


        builder.append("Facts:");
        builder.append(formatFacts(result.getFactIterator())).append("\n");

        builder.append("Next Characters:");

        builder.append(formatNextCharacters(result.getNextCharacterIterator()));

        return builder.toString();
    }

    /**
     * Format the next characters the following format [a,b,c]
     *
     * @param nextCharacterIterator An iterator to Character
     * @return
     */

    private String formatNextCharacters(Iterator<Character> nextCharacterIterator) {

        StringBuilder builder = new StringBuilder("[");
        while (nextCharacterIterator.hasNext()) {
            Character nextCharacter = nextCharacterIterator.next();
            builder.append(nextCharacter);
            if (nextCharacterIterator.hasNext()) {
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();

    }


    /**
     * Format the names of the facts in the following format [fact1_name, factn_name]
     *
     * @param factIterator An iterator to facts
     * @return
     */
    private String formatFacts(Iterator<Fact> factIterator) {
        //TODO: Eliminate duplication
        StringBuilder builder = new StringBuilder("[");
        while (factIterator.hasNext()) {
            String factName = factIterator.next().getFactName();
            builder.append(factName);
            if (factIterator.hasNext()) {
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
