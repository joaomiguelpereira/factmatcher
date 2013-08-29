package eu.jpereira.cli;

import com.google.inject.AbstractModule;
import eu.jpereira.*;

/**
 * Guice Module
 */
public class ApplicationModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(FactMatcher.class).to(SimpleFactMatcher.class);
        bind(FactReader.class).to(TrainStationFactReader.class);
        bind(ResultFormatter.class).to(SimpleResultFormatter.class);
        bind(ResultPrinter.class).to(ConsoleResultPrinter.class);
    }
}
