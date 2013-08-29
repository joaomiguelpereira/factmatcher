package eu.jpereira;
import static org.fest.assertions.Assertions.*;

import org.junit.Test;

/**
 * Test a fact
 */
public class FactTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFactWithNullName() {
        new Fact(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFactWithEmptyName() {
        new Fact("");
    }

    @Test
    public void shouldCreateFactWithEmptySpaces() {
       assertThat(new Fact(" ").getFactName()).isEqualTo(" ");
       assertThat(new Fact("  ").getFactName()).isEqualTo("  ");
       assertThat(new Fact("   ").getFactName()).isEqualTo("   ");
    }

    @Test
    public void shouldBeComparable() {
        //TODO: Complete tests
        assertThat(new Fact("a").compareTo(new Fact("b"))).isLessThan(0);
        assertThat(new Fact("c").compareTo(new Fact("b"))).isGreaterThan(0);
        assertThat(new Fact("a").compareTo(new Fact("a"))).isEqualTo(0);
        assertThat(new Fact(" ").compareTo(new Fact("b"))).isLessThan(0);
    }



}
