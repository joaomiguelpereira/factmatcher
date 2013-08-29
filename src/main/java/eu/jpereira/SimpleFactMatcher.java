package eu.jpereira;


import javax.inject.Inject;
import java.util.*;

/**
 * A simple implementation of a fact matcher
 */
public class SimpleFactMatcher implements FactMatcher {

    //Partition the facts according to the first character
    private Map<Character, Set<Fact>> facts;


    @Inject
    public SimpleFactMatcher(FactReader factReader) {
        if (factReader == null) {
            throw new IllegalArgumentException("FactReader cannot be null");
        }
        facts = new HashMap<Character, Set<Fact>>();
        buildFacts(factReader);

    }

    @Override
    public MatchResult match(String partialInput) {
        if (partialInput == null || partialInput.isEmpty()) {
            throw new IllegalArgumentException("Can't deal with null or empty input ");
        }
        Set<Fact> storedFacts = getFactPartitionOrEmpty(partialInput.toLowerCase().charAt(0));
        Iterator<Fact> factsIterator = storedFacts.iterator();
        TreeSet<Fact> matchedFacts = new TreeSet<Fact>();
        TreeSet<Character> nextCharacters = new TreeSet<Character>();

        while (factsIterator.hasNext()) {
            Fact fact = factsIterator.next();
            addFactsAndCharacterIfMatch(fact, partialInput, matchedFacts, nextCharacters);
        }
        if (matchedFacts.size() > 0) {

            return new MatchResult(matchedFacts, nextCharacters);
        } else {
            return new EmptyMatchResult();
        }
    }

    /**
     * Build Internal data structure from a fact reader
     *
     * @param factReader
     */
    private void buildFacts(FactReader factReader) {

        Iterator<String> stationIterator = factReader.iterator();
        while (stationIterator.hasNext()) {
            String factName = stationIterator.next();
            addFactToPartition(factName);
        }
    }

    /**
     * Add a fact to the corresponding set, based on first character
     *
     * @param factName
     */
    private void addFactToPartition(String factName) {
        Character character = factName.toLowerCase().charAt(0);
        Set<Fact> partitionedFacts = facts.get(character);
        Fact fact = new Fact(factName);
        if (partitionedFacts == null) {
            partitionedFacts = new TreeSet<Fact>();
            partitionedFacts.add(fact);
            facts.put(character, partitionedFacts);
        } else if (!partitionedFacts.contains(fact)) {
            partitionedFacts.add(fact);
        }
    }


    /**
     * If the partial input matches some the fact, it is added to the set of facts and the next character added to the next possible characters, if any
     *
     * @param fact           The fact to compare with
     * @param partialInput   The partial input
     * @param matchedFacts   The set to add the fact if matched
     * @param nextCharacters The set to add next character of any
     */
    private void addFactsAndCharacterIfMatch(Fact fact, String partialInput, TreeSet<Fact> matchedFacts, TreeSet<Character> nextCharacters) {
        if (fact.getFactName().toLowerCase().startsWith(partialInput.toLowerCase())) {
            matchedFacts.add(fact);
            addNextCharacterToCharacterSet(fact, partialInput,nextCharacters);
        }
    }

    /**
     * If exists, adds the next character to the set of characters
     * @param fact The matched fact
     * @param partialInput The partial input
     * @param nextCharacters the set where to add the character if any
     */
    private void addNextCharacterToCharacterSet(Fact fact, String partialInput, TreeSet<Character> nextCharacters) {

        if (fact.getFactName().length() > partialInput.length()) {
            Character nextChar = fact.getFactName().charAt(partialInput.length());
            nextCharacters.add(Character.toUpperCase(nextChar));
        }

    }

    /**
     * Get the Partition Set hashed by the character or create new one if none exists
     *
     * @param character The character identifying the partition
     * @return
     */
    private Set<Fact> getFactPartitionOrEmpty(Character character) {

        if (!this.facts.containsKey(character)) {
            return Collections.emptySet();
        }
        return this.facts.get(character);
    }
}
