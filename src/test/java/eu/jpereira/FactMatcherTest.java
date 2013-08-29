package eu.jpereira;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

/**
 * Test class for FactMatcher
 */
public class FactMatcherTest {

    private FactMatcher matcherUndeTest;

    @Before
    public void setup() {
        matcherUndeTest = new SimpleFactMatcher(new TestFactReader());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailForNullInput() {
        matcherUndeTest.match(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailForEmptyInput() {
        matcherUndeTest.match("");
    }


    @Test
    public void shouldGetNothing() {
        assertThat(matcherUndeTest.match(" ").getClass()).isEqualTo(EmptyMatchResult.class);
    }

    @Test
    public void shouldCreateMatchResult() {

        //Fake Feeder has "Aveiro" and "Amarante"
        MatchResult matchResult = matcherUndeTest.match("a");
        assertThat(matchResult).isNotNull();

        assertThat(matchResult.getFactIterator().hasNext()).isTrue();

        Iterator<Fact> matches = matchResult.getFactIterator();

        assertThat(matches.next()).isEqualTo(new Fact("Amarante"));
        assertThat(matches.next()).isEqualTo(new Fact("Aveiro"));
        assertThat(matches.hasNext()).isFalse();


        assertThat(matchResult.getNextCharacterIterator().hasNext()).isTrue();
        Iterator<Character> nextCharacters = matchResult.getNextCharacterIterator();

        assertThat(nextCharacters.next()).isEqualTo('m');
        assertThat(nextCharacters.next()).isEqualTo('v');
        assertThat(nextCharacters.hasNext()).isFalse();

    }

    @Test
    public void scenario1() {

        matcherUndeTest = new SimpleFactMatcher(new TestFactReader("DARTFORD", "DARTMOUTH", "TOWER HILL", "DERBY"));

        MatchResult matchResult = matcherUndeTest.match("DART");
        assertThat(matchResult).isNotNull();
        assertThat(matchResult.getFactIterator().hasNext()).isTrue();

        Iterator<Fact> matches = matchResult.getFactIterator();

        assertThat(matches.next()).isEqualTo(new Fact("DARTFORD"));
        assertThat(matches.next()).isEqualTo(new Fact("DARTMOUTH"));
        assertThat(matches.hasNext()).isFalse();
        assertThat(matchResult.getNextCharacterIterator().hasNext()).isTrue();

        Iterator<Character> nextCharacters = matchResult.getNextCharacterIterator();

        assertThat(nextCharacters.next()).isEqualTo('F');
        assertThat(nextCharacters.next()).isEqualTo('M');
        assertThat(nextCharacters.hasNext()).isFalse();
    }

    @Test
    public void scenario2() {

        matcherUndeTest = new SimpleFactMatcher(new TestFactReader("LIVERPOOL", "LIVERPOOL LIME STREET", "PADDINGTON"));

        MatchResult matchResult = matcherUndeTest.match("LIVERPOOL");
        assertThat(matchResult).isNotNull();

        assertThat(matchResult.getFactIterator().hasNext()).isTrue();

        Iterator<Fact> matches = matchResult.getFactIterator();


        assertThat(matches.next()).isEqualTo(new Fact("LIVERPOOL"));
        assertThat(matches.next()).isEqualTo(new Fact("LIVERPOOL LIME STREET"));
        assertThat(matches.hasNext()).isFalse();

        assertThat(matchResult.getNextCharacterIterator().hasNext()).isTrue();

        Iterator<Character> nextCharacters = matchResult.getNextCharacterIterator();

        assertThat(nextCharacters.next()).isEqualTo(' ');
        assertThat(nextCharacters.hasNext()).isFalse();
    }
    @Test
    public void scenario3() {

        matcherUndeTest = new SimpleFactMatcher(new TestFactReader("EUSTON","LONDON BRIDGE", "VICTORIA"));
        MatchResult matchResult = matcherUndeTest.match("KINGS CROSS");
        assertThat(matchResult).isNotNull();
        assertThat(matchResult.getFactIterator().hasNext()).isFalse();
        assertThat(matchResult.getNextCharacterIterator().hasNext()).isFalse();
    }




}
