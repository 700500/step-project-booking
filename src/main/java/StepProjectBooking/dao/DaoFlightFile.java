package StepProjectBooking.dao;

import StepProjectBooking.domain.Flight;
import StepProjectBooking.domain.FlightFinder;
import StepProjectBooking.utils.CustomUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DaoFlightFile implements DAO<Flight> {
    private File file;

    public DaoFlightFile(String filename) {
        this.file = new File(filename);
    }


    @Override
    public Optional<Flight> getByID(int id) {
        return getAll().stream().filter(s -> s.getId() == id).findFirst();
    }

    @Override
    public Collection<Flight> rejectById(int id) {
        return null;
    }

    @Override
    public Collection<Flight> getAll() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return br.lines().map(Flight::parse)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public Collection<Flight> getMyFlights(String name, String surname) {
        return getAll().stream().filter(flight -> flight.equals(name)).collect(Collectors.toList());
    }

    @Override
    public Collection<Flight> getAllByInfo(FlightFinder flightFinder) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return br.lines().
                    map(Flight::parse)
                    .filter(flight -> flight.getDestination().equalsIgnoreCase(flightFinder.getDestination()))
                    .filter(flight -> flight.getFreeSeats() >= flightFinder.getPassengersCount())
                    .filter(flight -> CustomUtils.parseStringToLocalDateTime(flight.getTime()).toLocalDate().equals(flightFinder.getTime()))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public int exit(int command) {
        return 0;
    }

    @Override
    public void createAll(Collection<Flight> data) {
        write(data);
    }

    private void write(Collection<Flight> flights) {
        try (FileWriter oos = new FileWriter(file, true)) {
            oos.append(flights.stream().map(Flight::represent).collect(Collectors.joining("\n")));
            oos.append("\n");
        } catch (IOException ex) {
            throw new RuntimeException("DAO:write:IOException", ex);
        }
    }

    private void clearAndWrite(Collection<Flight> flights) {
        try (FileWriter oos = new FileWriter(file)) {
            oos.append(flights.stream().map(Flight::represent).collect(Collectors.joining("\n")));
            oos.append("\n");
        } catch (IOException ex) {
            throw new RuntimeException("DAO:write:IOException", ex);
        }
    }

    @Override
    public void changeTheNumberOfFreeSeats(int flightId, int count) {
        List<Flight> flights = getAll().stream().peek(flight -> {
            if (flight.getId() == flightId) {
                flight.setFreeSeats(flight.getFreeSeats() + count);
            }
        }).collect(Collectors.toList());
        clearAndWrite(flights);
    }
}
