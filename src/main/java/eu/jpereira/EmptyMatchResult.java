package eu.jpereira;

import java.util.Collections;

/**
 * Model an empty Match Result
 */
public class EmptyMatchResult extends MatchResult {

    public EmptyMatchResult() {
        super(Collections.<Fact>emptySet(), Collections.<Character>emptySet());
    }
}
