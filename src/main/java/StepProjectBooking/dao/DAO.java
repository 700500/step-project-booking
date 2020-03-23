package StepProjectBooking.dao;

import StepProjectBooking.domain.FlightFinder;

import java.util.Collection;
import java.util.Optional;

public interface DAO<A> {
    Optional<A> getByID(int id);
    Collection<A> getAll();
    Collection<A> getAllByInfo(FlightFinder flightFinder);
    Collection<A> getMyFlights(String name, String surname);
    Collection<A> rejectById(int  id);
    int exit(int command);
    void createAll(Collection<A> data);
    void changeTheNumberOfFreeSeats(int id, int count);
}
