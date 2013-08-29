package eu.jpereira.cli;

import eu.jpereira.MatchResult;

import javax.inject.Inject;

/**
 * Prints a result to the System console
 */
public class ConsoleResultPrinter implements ResultPrinter {

    private final ResultFormatter formatter;


    @Inject
    public ConsoleResultPrinter(ResultFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void print(MatchResult result) {
        System.out.println(formatter.format(result));
    }
}
