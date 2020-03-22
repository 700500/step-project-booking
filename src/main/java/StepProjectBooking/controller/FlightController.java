package StepProjectBooking.controller;

import StepProjectBooking.domain.Flight;
import StepProjectBooking.domain.FlightFinder;
import StepProjectBooking.exception.CustomException;
import StepProjectBooking.service.FlightService;

import java.util.Collection;

public class FlightController {

    private FlightService flightService=new FlightService();

    public String getAllFlights() {
        Collection<String> allFlights = flightService.getAllFlights();
        return allFlights.toString();
    }

    public String getById(int id) {
        return flightService.getById(id).orElseThrow(()-> new CustomException("Flight with this id does not exists"));
    }

    public int exitFrom(int command){
        return flightService.exitFrom(command);
    }

    public Collection<String> info(FlightFinder flightFinder) {
        return flightService.getinfo(flightFinder);
    }

    public String bookById(int id){
        return flightService.getById(id).orElseThrow(()->new CustomException("Flight with this id does not exists"));
    }

    public int lastFlightsId(){
        return flightService.lastFlightsId();
    }

    public void writeFlights(Collection<Flight> flights) {
        flightService.writeFlights(flights);
    }
}
