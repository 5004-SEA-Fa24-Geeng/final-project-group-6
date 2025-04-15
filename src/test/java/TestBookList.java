import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBookList {

    static Set<IBook> books;
    IBookList bookList;

    @TempDir
    static Path tempDir = Path.of("temp");

    @BeforeEach
    public  void setup() {
        books = new HashSet<>();
        books.add(new StandardBook("123456","The Great Book","Jane Doe","Fiction","Available"));
        books.add(new IllegalBook("654321","Learning Java","John Smith","Education","Available"));

        bookList = new BookList();
    }

    @Test
    public void testAddToList(){
        bookList.addToList("123456", books.stream());
        assertEquals(1, bookList.count());
    }

    @Test
    public void testRemoveFromList(){
        bookList.addToList("123456", books.stream());
        bookList.removeFromList("123456");
        assertEquals(0, bookList.count());
    }

    @Test
    public void testUpdateLibrary() throws IOException{
        String libFile = tempDir.resolve("src/output/Library.csv").toString();
        String testISBN = "123456";

        Files.write(Path.of(libFile), List.of(
                "ISBN,Title,Author,Type,Status",
                "123456,The Great Book,Jane Doe,STANDARD,available"
        ));


        bookList.updateLibrary(libFile, testISBN, "unavailable");
        List<String> lines = Files.readAllLines(Path.of(libFile));
        assertTrue(lines.stream().anyMatch(line -> line.contains(testISBN) && line.contains("unavailable")));
    }

    @Test
    public void testUpdateCheckoutFile() throws IOException {
        String outputFile = tempDir.resolve("testOutputFile.csv").toString();
        IBook book = new StandardBook("123456", "The Great Book", "Jane Doe", "Fiction", "unavailable");

        bookList.updateCheckoutFile(outputFile, book);
        List<String> lines = Files.readAllLines(Path.of(outputFile));

        assertTrue(lines.stream().anyMatch(line -> line.contains("123456") && line.contains("available")));
    }


}
