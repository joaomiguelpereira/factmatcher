package eu.jpereira.cli;

import eu.jpereira.MatchResult;

/**
 * A formater responsability is to take a MatchResult and format it to a human readable string
 */
public interface ResultFormatter {

    /**
     * Format a MetchResult according to the strategy implemented
     * @param result
     * @return
     */
    public String format(MatchResult result);

}
