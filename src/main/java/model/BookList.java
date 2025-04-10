package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class BookList implements IBookList{

    /**
     * Set of books.
     */
    private Set<IBook> books;

    /**
     * Constructor for bookList.
     */
    public BookList(){books = new HashSet<>();}

    /**
     *
     * @return list of Books.
     */
    @Override
    public List<IBook> getBooks(){
        return books.stream().sorted().collect(Collectors.toList());
    }


}
