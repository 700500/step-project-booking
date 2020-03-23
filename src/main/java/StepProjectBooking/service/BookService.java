package StepProjectBooking.service;

import StepProjectBooking.domain.Book;
import StepProjectBooking.domain.Flight;
import StepProjectBooking.domain.FlightFinder;
import StepProjectBooking.domain.Passenger;
import StepProjectBooking.dao.DAO;
import StepProjectBooking.dao.DaoBookFile;
import StepProjectBooking.exception.CustomException;
import StepProjectBooking.utils.CustomUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookService {
    private DAO<Book> daoBookFile=new DaoBookFile("src\\main\\java\\StepProjectBooking\\filesTxt\\Book.txt");
    private final FlightService flightService = new FlightService();

    public Collection<String> getAllBooks() {
        return daoBookFile.getAll().stream().map(Book::show).collect(Collectors.toList());
    }


    public Collection<String> getAllMyFlights(String name, String surname){
        return daoBookFile.getMyFlights(name,surname).stream().
                map(Book::getFlightId).
                map(id->flightService.getById(id).get()).
                collect(Collectors.toList());
    }

    public Collection<String> rejectMyBooking(int id){
        if (daoBookFile.getByID(id).isPresent()){
            flightService.changeTheNumberOfFreeSeats(daoBookFile.getByID(id).get().getFlightId(),1);
            return daoBookFile.rejectById(id).stream().map(Book::represent).collect(Collectors.toList());
        }else throw  new CustomException("Please, input the valid id of ticket: ");
    }

    public int lastBookId() {
        int count = daoBookFile.getAll().size();
        if (count == 0) return count;
        return daoBookFile.getAll().stream().skip(count-1).findFirst().get().getId();
    }

    public void writeBook(Collection<Book> books) {
        daoBookFile.createAll(books);
    }

    public String book(Collection<String> flights, FlightFinder flightFinder) {
        System.out.println("1) If you want to book ticket\n" +
                "* Please, enter the serial number of the flight: \n" +
                "2) If you do not want to book ticket\n" +
                "* Please, enter 0 to go the main menu\n");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        if (num == 0) return "The main menu";
        String flightForBook = flights.stream()
                .filter(s -> s.split(":")[1].split(";")[0].equalsIgnoreCase(String.valueOf(num)))
                .findFirst()
                .orElseThrow(() -> new CustomException("The serial number is invalid"));
        Integer flightId = Integer.valueOf(flightForBook.split(":")[1].split(";")[0]);
        System.out.println(flightForBook);
        int countForBook = flightFinder.getPassengersCount();
        int lastIndex = lastBookId();
        Collection<Book> bookCollection = new ArrayList<>();
        while (countForBook-->0){
            System.out.println("Please enter your name:" );
            String name = in.next();
            System.out.println("Please, enter your surname: ");
            String surname = in.next();
            Book book = new Book(++lastIndex,flightId,
                    new Passenger(name,surname));
            bookCollection.add(book);
        }
        writeBook(bookCollection);
        flightService.changeTheNumberOfFreeSeats(flightId,-bookCollection.size());
        return bookCollection.toString();
    }

}
