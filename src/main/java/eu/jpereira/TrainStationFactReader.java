package eu.jpereira;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A Train Station Reader
 */
public class TrainStationFactReader implements FactReader {

    List<String> stations = new ArrayList<String>();

    public TrainStationFactReader() {
        readSource();
    }

    private void readSource() {

        InputStream stream = TrainStationFactReader.class.getClassLoader().getResourceAsStream("stations.txt");
        if (stream == null ) {
            throw new RuntimeException("Could not load stations.txt from classpath");
        }

        BufferedReader bReader = new BufferedReader(new InputStreamReader(stream));

        String station;
        try {
            while ((station = bReader.readLine()) != null ) {
                if (!station.trim().isEmpty()) {
                    stations.add(station.trim());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Train Stations",e);
        }
    }

    @Override
    public Iterator<String> iterator() {
        return stations.iterator();
    }
}
