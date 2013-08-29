package eu.jpereira;


import eu.jpereira.cli.ResultFormatter;
import org.fest.assertions.Assertions;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;


/**
 * Test the simple formatter
 */
public class SimpleResultFormatterTest {

    private ResultFormatter formatter = new SimpleResultFormatter();


    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWithNullResult() {
        formatter.format(null);
    }

    @Test
    public void shouldFormatNotResult() {
        MatchResult emptyMatchResult = new EmptyMatchResult();
        String result = formatter.format(emptyMatchResult);
        Assertions.assertThat(result).isEqualTo("Facts:[]\nNext Characters:[]");
    }

    @Test
    public void shouldFormatFactsAndCharacters() {
        Set<Fact> dummyFacts = new TreeSet<Fact>();
        Set<Character> characterSet = new TreeSet<Character>();

        dummyFacts.add(new Fact("Aveiro"));
        dummyFacts.add(new Fact("Lisboa"));

        characterSet.add('a');
        characterSet.add('b');

        MatchResult matchResult = new MatchResult(dummyFacts,characterSet);
        String result = formatter.format(matchResult);
        Assertions.assertThat(result).isEqualTo("Facts:[Aveiro,Lisboa]\nNext Characters:[a,b]");
    }


}
