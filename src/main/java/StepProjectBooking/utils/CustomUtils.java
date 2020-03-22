package StepProjectBooking.utils;

import StepProjectBooking.domain.Book;
import StepProjectBooking.controller.BookController;
import StepProjectBooking.domain.FlightFinder;
import StepProjectBooking.domain.Passenger;
import StepProjectBooking.exception.CustomException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Scanner;

public class CustomUtils {

    public static LocalDateTime parseStringToLocalDateTime(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        if (Objects.nonNull(date) && !date.trim().isEmpty()) {
            try {
                return LocalDateTime.parse(date, dateTimeFormatter);
            } catch (DateTimeParseException ex) {
                System.err.println(ex.getMessage());
                return null;
            }
        } else return null;
    }

    public static LocalDate parseStringToLocalDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (Objects.nonNull(date) && !date.trim().isEmpty()) {
            try {
                return LocalDate.parse(date, dateTimeFormatter);
            } catch (DateTimeParseException ex) {
                System.err.println(ex.getMessage());
                return null;
            }
        } else return null;
    }


    public static FlightFinder enterInfo() {
        FlightFinder flightFinder = new FlightFinder();
        Scanner in = new Scanner(System.in);
        System.out.println("Destination: ");
        flightFinder.setDestination(in.nextLine());
        System.out.println("Enter time in this format - - > {dd/MM/yyyy}");
        LocalDate localDate = parseStringToLocalDate(in.nextLine());
        while (localDate == null){
            System.out.println("Please enter a valid date : ");
            localDate = parseStringToLocalDate(in.nextLine());
        }
        flightFinder.setTime(localDate);
        System.out.println("Passenger count: ");
        flightFinder.setPassengersCount(in.nextInt());
        return flightFinder;
    }


    public static String book(Collection<String> flights, FlightFinder flightFinder) {
        BookController bookController=new BookController();
        System.out.println("1) If you want to book ticket\n" +
                "* Please, enter the serial number of the flight: \n" +
                "2) If you do not want to book ticket\n" +
                "* Please, enter 0 to go the main menu\n");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        if (num == 0) return "The main menu";
        String flightForBook = flights.stream()
                .filter(s -> s.split(":")[0].equalsIgnoreCase(String.valueOf(num)))
                .findFirst()
                .orElseThrow(() -> new CustomException("The serial number is invalid"));
        System.out.println(flightForBook);
        int countForBook = flightFinder.getPassengersCount();
        int lastIndex = bookController.lastBookId();
        Collection<Book> bookCollection = new ArrayList<>();
        while (countForBook-->0){
            System.out.println("Please enter your name:" );
            String name = in.next();
            System.out.println("Please, enter your surname: ");
            String surname = in.next();
            Book book = new Book(lastIndex++,Integer.parseInt(flightForBook.split(":")[0]),
                    new Passenger(name,surname));
            bookCollection.add(book);
        }
        bookController.writeBook(bookCollection);
        return bookCollection.toString();
    }


}
