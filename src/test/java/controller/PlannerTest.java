package controller;

import model.IBook;
import model.StandardBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PlannerTest {

    static Set<IBook> books;

    @BeforeEach
    public void setUp() {
        books = new HashSet<>();
        IBook book1 = new StandardBook("12345678910","The Great Book","Jane Doe","Fiction","Available");
        IBook book2 = new StandardBook("10987654321","The Bad Book","Jon Doe","NonFiction","Available");
        IBook book3 = new StandardBook("10987654320","The Bad Book 2","Jonny Doe","NonFiction","Available");

        books.add(book1);
        books.add(book2);
        books.add(book3);
    }

    @Test
    public void testFilterISBN() {
        IPlanner planner = new Planner(books);
        List<IBook> filtered = planner.filter("ISBN == 10987654321").toList();
        assertEquals(1, filtered.size());
        assertEquals("10987654321", filtered.get(0).getISBN());
        List<IBook> filtered2 = planner.filter("ISBN ~= 123").toList();
        assertEquals(1, filtered.size());
        assertEquals("12345678910", filtered.get(0).getISBN());
    }

    @Test
    void reset() {
    }
}