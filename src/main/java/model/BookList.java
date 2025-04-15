package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class BookList implements IBookList {

    /**
     * Set of books.
     */
    private Set<IBook> books;

    /**
     * Constructor for bookList.
     */
    public BookList() {
        books = new HashSet<>();
    }

    /**
     * @return list of Books.
     */
    @Override
    public List<IBook> getBooks() {
        return books.stream().sorted().collect(Collectors.toList());
    }

    /**
     * clear all books from list.
     */
    @Override
    public void clear() {
        books.clear();
    }

    /**
     * books to add to (check in or check out) list.
     *
     * @param input     String to parse.
     * @param filtered filtered collection to use for adding.
     * @throws IllegalArgumentException if string is invalid.
     */
    @Override
    public void addToList(String input, Stream<IBook> filtered) throws IllegalArgumentException {
        List<IBook> filteredList = filtered.toList();
        boolean added = false;

        for (IBook book : filteredList) {
            if (input.equalsIgnoreCase(book.getISBN()) ||
                    input.equalsIgnoreCase(book.getBookTitle()) ||
                    input.equalsIgnoreCase(book.getAuthor()) ||
                    input.equalsIgnoreCase(book.getCategory())) {

                books.add(book);
                added = true;
            }
        }

        if (!added) {
            throw new IllegalArgumentException("No match found for input: " + input);
        }
    }

    /**
     *
     * @param filename file containing library books.
     * @param isbnToUpdate isbn for book that needs updating.
     * @param newStatus status you want to change to(available/unavailable).
     */

    @Override
    public void updateLibrary(String filename, String isbnToUpdate, String newStatus) {
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String header = reader.readLine();
            if (header != null) {
                updatedLines.add(header);
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5 && parts[0].equalsIgnoreCase(isbnToUpdate)) {
                    parts[4] = newStatus;
                    line = String.join(",", parts);
                }
                updatedLines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filename + " → " + e.getMessage());
            return;
        }

        // Write the file back with updated lines
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String updatedLine : updatedLines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing updated book status to file: " + e.getMessage());
        }
    }

    /**
     *
     * @param filename designated checkout file.
     * @param book book to be checked out.
     */

    @Override
    public void updateCheckoutFile(String filename, IBook book) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))){
            String type = (book instanceof IllegalBook)? "ILLEGAL" : "STANDARD";
            writer.write(String.format("%s,%s,%s,%s,%s",
                    book.getISBN(),
                    book.getBookTitle(),
                    book.getAuthor(),
                    type,
                    book.getStatus()
            ));
            writer.newLine();
        }catch(IOException e){
            System.err.println("Error writing book to checkout file: " + e.getMessage());
        }
    }

    /**
     * remove book from list by isbn
     * @param input data of book to remove.
     */

    @Override
    public void removeFromList(String input) throws IllegalArgumentException{
        IBook bookToRemove = null;
        String inputLC = input.toLowerCase();

        for (IBook book : books) {
            if (book.getISBN().equalsIgnoreCase(input) ||
                    book.getBookTitle().toLowerCase().contains(inputLC) ||
                    book.getAuthor().toLowerCase().contains(inputLC) ||
                    book.getCategory().toLowerCase().contains(inputLC)) {

                bookToRemove = book;
                break;
            }
        }

        if (bookToRemove != null) {
            books.remove(bookToRemove);
        } else {
            throw new IllegalArgumentException("No matching book found to remove for input: " + input);
        }
    }

    public int count(){
        return books.size();
    }

}
