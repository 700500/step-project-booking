package StepProjectBooking.generator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Cities {

    private static final List<String> cities = Arrays.asList(
            "Baku", "Moscow", "Berlin", "Minsk", "Brussels",
            "Zagreb", "Copenhagen", "Helsinki", "Vienna",
            "Paris", "Athens", "Budapest", "Rome", "Riga",
            "Vilnius", "Podgorica", "Amsterdam", "Oslo",
            "Skopje", "Warsaw", "Lisbon", "Bucharest",
            "Belgrade", "Madrid", "Bern", "Stockholm",
            "London");

    public static String getRandomCity() {
        return cities.get(new Random().nextInt(cities.size()));
    }
}
