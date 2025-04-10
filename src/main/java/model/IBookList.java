package model;

import java.util.List;
import java.util.stream.Stream;

public interface IBookList {

    String ADD_ALL = "all";

    List<IBook> getBooks();

    void addToCheckoutList(String str, Stream<IBook> filtered) throws IllegalArgumentException;

    void addToCheckinList(String str, Stream<IBook> filtered) throws IllegalArgumentException;

    void saveCheckinBooksToFile(String filename);

    void saveCheckoutBooksToFile(String filename);

    void clear();

    void removeFromList(String str) throws IllegalArgumentException;
}
