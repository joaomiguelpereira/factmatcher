package eu.jpereira;

import org.fest.assertions.Assertions;
import org.junit.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Test the Match Result
 */
public class MatchResultTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailToCreateWithNullFactSet() {
        new MatchResult(null, Collections.<Character>emptySet());
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldFailToCreateWithNullCharacterSet() {
        new MatchResult(Collections.<Fact>emptySet(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailToCreateWithNullCharacterSetAndFactSet() {
        new MatchResult(null, null);
    }

    @Test
    public void shouldRespondWithCorrectIterator() {
        Set<Fact> factSet = new TreeSet<Fact>();
        factSet.add(new Fact("Dummy"));


        Set<Character> characterSet = new TreeSet<Character>();
        characterSet.add('a');

        MatchResult result = new MatchResult(factSet, characterSet);

        Iterator<Fact> factIterator = result.getFactIterator();
        Assertions.assertThat(factIterator.next()).isEqualTo(new Fact("Dummy"));
        Assertions.assertThat(factIterator.hasNext()).isFalse();

        Iterator<Character> characterIterator = result.getNextCharacterIterator();
        Assertions.assertThat(characterIterator.next()).isEqualTo('a');
        Assertions.assertThat(characterIterator.hasNext()).isFalse();


    }

}
