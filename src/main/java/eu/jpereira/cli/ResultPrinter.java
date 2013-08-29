package eu.jpereira.cli;

import eu.jpereira.MatchResult;

/**
 * Responsible for printing the result
 */
public interface ResultPrinter {

    /**
     * Print the result
     * @param result
     */
    public void print(MatchResult result);
}
