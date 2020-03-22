package StepProjectBooking.dao;

import StepProjectBooking.domain.Book;
import StepProjectBooking.domain.FlightFinder;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class DaoBookFile implements DAO<Book> {

    private File file;

    public DaoBookFile(String filename) {
        this.file = new File(filename);
    }


    @Override
    public Optional<Book> getByID(int id) {
        return getAll().stream().filter(s -> s.getId() == id).findFirst();
    }

    @Override
    public Collection<Book> getAll() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return br.lines().map(Book::parse).collect(Collectors.toList());
        } catch (IOException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public Collection<Book> getMyFlights(String name, String surname) {
        return getAll().stream().filter(book -> book.getPassenger().getName().equalsIgnoreCase(name))
                .filter(book -> book.getPassenger().getSurname().equalsIgnoreCase(surname)).collect(Collectors.toList());
    }

    @Override
    public Collection<Book> getAllByInfo(FlightFinder flightFinder) {
        return null;
    }


    @Override
    public int exit(int command) {
        return 0;
    }

    @Override
    public Collection<Book> rejectById(int id) {
        Collection<Book> books = getAll().stream().filter(book -> book.getId() != id).collect(Collectors.toList());
        clearAndWrite(books);
        return books;
    }


    @Override
    public void createAll(Collection<Book> data) {
        write(data);
    }

    @Override
    public void changeTheNumberOfFreeSeats(int id, int count) {

    }


    private void write(Collection<Book> books) {
        try (FileWriter oos = new FileWriter(file, true)) {
            oos.append(books.stream().map(Book::represent).collect(Collectors.joining("\n")));
            oos.append("\n");
        } catch (IOException ex) {
            throw new RuntimeException("DAO:write:IOException", ex);
        }
    }

    private void clearAndWrite(Collection<Book> books) {
        try (FileWriter oos = new FileWriter(file, false)) {
            oos.append(books.stream().map(Book::represent).collect(Collectors.joining("\n")));
            if (books.size() > 0)
                oos.append("\n");
        } catch (IOException ex) {
            throw new RuntimeException("DAO:write:IOException", ex);
        }
    }


}
