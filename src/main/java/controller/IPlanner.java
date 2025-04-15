package controller;

import model.BookData;
import model.IBook;

import java.util.stream.Stream;

public interface IPlanner {
    Stream<IBook> filter(String filter);
    Stream<IBook> filter(String filter, BookData sortOn);
    Stream<IBook> filter(String filter, BookData sortOn, boolean ascending);
    void reset();
}