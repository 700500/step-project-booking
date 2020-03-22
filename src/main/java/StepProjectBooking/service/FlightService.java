package StepProjectBooking.service;

import StepProjectBooking.domain.Flight;
import StepProjectBooking.domain.FlightFinder;
import StepProjectBooking.dao.DAO;
import StepProjectBooking.dao.DaoFlightFile;
import StepProjectBooking.utils.CustomUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightService {

    private DAO<Flight> daoFlightFile = new DaoFlightFile("src\\main\\java\\StepProjectBooking\\filesTxt\\Flights.txt");

    public Collection<String> getAllFlights() {
        return daoFlightFile.getAll().stream()
                .filter(flight ->
                        Duration.between(LocalDateTime.now(),CustomUtils.parseStringToLocalDateTime(flight.getTime()))
                                .getSeconds()<86400)
                .map(Flight::show).collect(Collectors.toList());
    }


    public Optional<String> getById(int id) {
        return daoFlightFile.getByID(id).map(Flight::show);
    }

    public Collection<String> getinfo(FlightFinder flightFinder) {
        return daoFlightFile.getAllByInfo(flightFinder).stream()
                .map(Flight::show)
                .collect(Collectors.toList());
    }

    public int exitFrom(int command) {
        return daoFlightFile.exit(command);
    }

    public int lastFlightsId() {
        return daoFlightFile.getAll().size() > 1 ? daoFlightFile.getAll().size()+1 : 1;
    }

    public void changeTheNumberOfFreeSeats(int flightId, int count) {
        daoFlightFile.changeTheNumberOfFreeSeats(flightId, count);
    }

    public void writeFlights(Collection<Flight> flights) {
        daoFlightFile.createAll(flights);
    }


}
