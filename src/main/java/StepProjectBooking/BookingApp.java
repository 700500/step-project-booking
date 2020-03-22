package StepProjectBooking;
import StepProjectBooking.generator.FlightRandomGenerator;
import StepProjectBooking.utils.Console;

public class BookingApp{

    public static void main(String[] args) {

        Console console= new Console();

        while (true){
            FlightRandomGenerator.generateRandomFlights();
            console.run();
        }
    }
}
