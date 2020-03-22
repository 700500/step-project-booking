package StepProjectBooking.controller;

import StepProjectBooking.domain.Book;
import StepProjectBooking.domain.FlightFinder;
import StepProjectBooking.service.BookService;

import java.util.Collection;

public class BookController {
    private final FlightController flightController=new FlightController();
    private BookService bookService=new BookService();

    public String getByNameAndSurname(String name, String surname){
        Collection<String> myFlights = bookService.getAllMyFlights(name,surname);
        return myFlights.toString();
    }

    public String rejectMyBook(int id){
        Collection<String> myBookings = bookService.rejectMyBooking(id);
        return myBookings.toString();
    }

    public int lastBookId() {
        return bookService.lastBookId();
    }

    public void writeBook(Collection<Book> books) {
        bookService.writeBook(books);
    }

    public void searchAndBook(FlightFinder flightFinder){
        Collection<String> flights = flightController.info(flightFinder);
        System.out.println(flights.toString());
        bookService.book(flights,flightFinder);
    }

    public String book(Collection<String> flights, FlightFinder flightFinder) {
        return bookService.book(flights,flightFinder);
    }
}
