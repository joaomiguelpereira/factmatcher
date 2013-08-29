package eu.jpereira;

/**
 * A Fact Matcher is an object that know all the facts,
 * provided by a fact feeder, and can guess facts on behalf of it's
 * users based on a partial input of the fact.
 *
 * A fact matcher is an object that requires resources to instantiate,
 * as it may manipulate all facts provided by the underlying FactReader before is ready to operate.
 *
 */
public interface FactMatcher {
    /**
     * Perform a match for the partialInput
     * @param partialInput Should not be a null partial input. Case Insensitive
     * @return a MatchResult
     */
    MatchResult match(String partialInput);
}
