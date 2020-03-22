package StepProjectBooking.domain;

public class Passenger {
    private int id;
    private String name;
    private String surname;

    public Passenger(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Passenger(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public static Passenger parse(String line) {
        String[] chunks = line.split(":");
        return new Passenger(
                Integer.parseInt(chunks[0]),
                chunks[1],
                chunks[2]
        );
    }

    public String represent() {
        return String.format("%d:%s:%s", id, name, surname);
    }


    @Override
    public String toString() {
        return String.format("Passenger[id=%d, name='%s', surname=%s]\n", id, name, surname);
    }


}
