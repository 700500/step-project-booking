package StepProjectBooking.utils;

import StepProjectBooking.domain.FlightFinder;
import StepProjectBooking.controller.BookController;
import StepProjectBooking.controller.FlightController;
import StepProjectBooking.exception.CustomException;
import StepProjectBooking.utils.CustomUtils;

import java.util.Scanner;

public class Console {

    private void myPrint(String message){ System.out.print(message); }

   public void run(){

        myPrint("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n" +
                "* Please, select one of the following commands by number  * \n" +
                "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n" +
                "*  [1] Display all flights                                *\n" +
                "*  [2] Display info about flight                          *\n" +
                "*  [3] Search and book flight                             *\n" +
                "*  [4] Cancel booking                                     *\n" +
                "*  [5] My flights                                         *\n" +
                "*  [6] Exit                                               *\n" +
                "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *" +
                "\n--->\n");

        Scanner in=new Scanner(System.in);
        int number=in.nextInt();
        BookController bookController=new BookController();
        FlightController flightController=new FlightController();
        switch (number){
            case 1:
                System.out.println(flightController.getAllFlights());
                break;
            case 2:
                System.out.println("Input id of the flight: ");
                int id=in.nextInt();
                try {
                    System.out.println(flightController.getById(id));
                } catch (CustomException ex){
                    System.err.println(ex.getMessage());
                }
                break;
            case 3:
                FlightFinder flightFinder = CustomUtils.enterInfo();
                bookController.searchAndBook(flightFinder);
                break;
            case 4:
                System.out.println(bookController.getAllBooks());
                int bookingId;
                System.out.println("Please, enter id of Booking: ");
                if (in.hasNextInt()) {
                    bookingId = in.nextInt();
                    try {
                        System.out.println(bookController.rejectMyBook(bookingId));
                    }catch (CustomException ex){
                        System.err.println(ex.getMessage());
                    }
                } else {
                    System.err.println("Your id is not valid!");
                }

                break;
            case 5:
                String name;
                String surname;
                System.out.println("Enter name");
                name=in.next();
                System.out.println("Enter surname");
                surname=in.next();
                System.out.println(bookController.getByNameAndSurname(name,surname));
                break;
            case 6:
                System.exit(flightController.exitFrom(number));
                break;

        }



    }
}
