package model;

import java.util.List;
import java.util.stream.Stream;

public interface IBookList {


    List<IBook> getBooks();

    void addToList(String str, Stream<IBook> filtered) throws IllegalArgumentException;


    void clear();

    void updateLibrary(String filename, String isbnToUpdate, String newStatus);

    void updateCheckoutFile(String filename, IBook book);

    void removeFromList(String str);

    int count();
}
