import model.IBook;
import model.StandardBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBook {

    static Set<IBook> books;

    @BeforeEach
    public void setUp(){
         books = new HashSet<>();
         books.add(new StandardBook("123456", "The Great Book", "Jane Doe", "Fiction", "Available"));
    }


    @Test
    public void getBookTitle(){

        for(IBook book : books){
            if(book.getBookTitle() == "Short Name"){
                assertEquals("Short Name", book.getBookTitle());
            }
        }
    }
}
