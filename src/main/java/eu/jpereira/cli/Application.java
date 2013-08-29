package eu.jpereira.cli;

import com.google.inject.Guice;
import com.google.inject.Injector;
import eu.jpereira.FactMatcher;
import eu.jpereira.MatchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Command Line app
 */
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        new Application().start();

    }


    /**
     * Start the command line application
     */
    private void start() {
        Injector injector = Guice.createInjector(new ApplicationModule());
        FactMatcher matcher = injector.getInstance(FactMatcher.class);
        ResultPrinter consolePrinter = injector.getInstance(ResultPrinter.class);


        String userInput;
        while ((userInput = askUserForInput()) != null) {

            if (!userInput.isEmpty()) {

                long startTime = System.nanoTime();
                MatchResult result = matcher.match(userInput);
                long endTime = System.nanoTime();
                LOG.debug(String.format("Done in %s ms ( %s ns )", (endTime - startTime) / 1000000, (endTime - startTime)));

                consolePrinter.print(result);

            }

        }

    }

    /**
     * Read lines from the console. Return the user input or null if user input ends with \q
     *
     * @return String or null if input ends with \q
     */
    private String askUserForInput() {

        //Ask user for input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter partial name of the station and press <enter> to see the results or enter \\q to quit: ");

        String userInput;
        try {
            userInput = br.readLine();
            System.out.println(String.format("You entered: %s", userInput));
        } catch (IOException e) {
            throw new RuntimeException("Could not read from console", e);
        }

        if (!userInput.endsWith("\\q")) {
            return userInput;
        }
        return null;
    }
}
