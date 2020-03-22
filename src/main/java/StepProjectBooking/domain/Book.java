package StepProjectBooking.domain;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private int flightId;
    private Passenger passenger;

    public Book() {
    }

    public Book(int id, int flightId, Passenger passenger) {
        this.id = id;
        this.flightId = flightId;
        this.passenger = passenger;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public static Book parse(String line) {
        String[] chunks = line.split(":");
        return new Book(
                Integer.parseInt(chunks[0]),
                Integer.parseInt(chunks[1]),
                new Passenger(chunks[2],chunks[3])
                );
    }

    public String represent() {
        return String.format("%d:%d:%s:%s", id, flightId,
                passenger.getName(),passenger.getSurname());
    }

    @Override
    public String toString() {
        String sb = "Book{" + "id=" + id + ", flightId=" + flightId + ", " + passenger + '}';
        return sb;
    }
}
