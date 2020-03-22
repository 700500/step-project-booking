package StepProjectBooking.domain;

public class Flight {
    private   int id;
    private  String time;
    private  String destination;
    private  int freeSeats;

    public Flight() {
    }

    Flight(int id, String time, String destination, int freeSeats) {
        this.id = id;
        this.time = time;
        this.destination = destination;
        this.freeSeats=freeSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public static Flight parse(String line) {
        String[] chunks = line.split(":");
        return new Flight(
                Integer.parseInt(chunks[0]),
               chunks[1]+ ":" + chunks[2],
                chunks[3],
                Integer.parseInt(chunks[4])
        );
    }

    public String represent() {
        return String.format("%d:%s:%s:%d", id, time, destination, freeSeats);
    }

    public String show() {
        return String.format("Flight id:%d; Departure time:%s; Destination:%s; Available seats:%d\n", id, time, destination, freeSeats);
    }
    @Override
    public String toString() {
        return String.format("Flight[number=%d, time='%s', destination=%s, number of free seats=%d]", id, time, destination, freeSeats);
    }

}
