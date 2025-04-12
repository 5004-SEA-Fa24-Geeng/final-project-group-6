package model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;


/**
 * loads the books from a csv file to a set of book Objects.
 */
public class BookLoader {
    /**
     * standard csv delimiter.
     */
    private static final String DELIMITER = ",";

    /**
     * private constructor to prevent instantiation.
     */
    private BookLoader() {}


    /**
     *
     * @param standardBookFile
     * @param illegalBookFile
     * @return
     */
    public static Set<IBook> loadBooks(String standardBookFile, String illegalBookFile){

        Set<IBook> combinedBookSet = new HashSet<>();
        for(String file: new String[]{standardBookFile, illegalBookFile}){
            combinedBookSet.addAll(loadBooksFromFile(file));
        }
        return combinedBookSet;

    }
    /**
     * loads books file from csv file to a set of Book objects.
     */
    public static Set<IBook> loadBooksFromFile(String filename) {

        Set<IBook> books = new HashSet<>();
        List<String> lines;
        try{
            InputStream is =  BookLoader.class.getResourceAsStream(filename);
            if (is == null) {
                throw new RuntimeException("Could not load resource: " + filename);
            }
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            lines = reader.lines().collect(Collectors.toList());
        }catch(Exception e){
            System.err.println("Error reading file:"+ e.getMessage());
            return books;
        }
        if(lines == null || lines.isEmpty()){
            return books;
        }

        Map<BookData, Integer> columnMap = processHeader(lines.remove(0));
        books = lines.stream().map(line -> toBook(line, columnMap))
                .filter(book -> book != null).collect(Collectors.toSet());

        return books;
    }

    /**
     *
     * convert line from csv file to either a standard/illegal book object.
     * @param line line from csv file.
     * @param columnMap map of columns to index.
     * @return book object.
     */

     public static IBook toBook(String line, Map<BookData, Integer> columnMap){
        String[] columns = line.split(DELIMITER);
        if(columns.length < columnMap.values().stream().max(Integer::compareTo).get()){
            return null;
        }

        try{
            String type = columns[columnMap.get(BookData.TYPE)].toLowerCase();
            IBook book = null;
             switch(type){
                case "standard":
                    book = toStandardBook(columns, columnMap);
                    break;
                case "illegal":
                    book = toIllegalBook(columns, columnMap);
                    break;
                default:
                    break;
            }
            return book;
        }catch (Exception e){
            return null;
        }

    }

    /**
     * converts the line from the csv file to a standardBook.
     * @param columns the columns to convert.
     * @param columnMap the map of columns to index.
     * @return standardBook object.
     */
    private static IBook toStandardBook(String[] columns, Map<BookData, Integer> columnMap){

        try{
            IBook book = new StandardBook(columns[columnMap.get(BookData.ISBN)],
                    columns[columnMap.get(BookData.TITLE)],
                    columns[columnMap.get(BookData.AUTHOR)],
                    columns[columnMap.get(BookData.CATEGORY)],
                    columns[columnMap.get(BookData.STATUS)]);
            return book;
        }catch (Exception e) {
            return null;
        }

    }


    /**
     * converts the line from the csv file to a illegalBook.
     * @param columns the columns to convert.
     * @param columnMap the map of columns to index.
     * @return illegalBook object.
     */
    private static IBook toIllegalBook(String[] columns, Map<BookData, Integer> columnMap){

        try{
            IBook book = new IllegalBook(
                    columns[columnMap.get(BookData.ISBN)],
                    columns[columnMap.get(BookData.TITLE)],
                    columns[columnMap.get(BookData.AUTHOR)],
                    columns[columnMap.get(BookData.CATEGORY)],
                    columns[columnMap.get(BookData.STATUS)]);
            return book;
        }catch (Exception e) {
            return null;
        }

    }

    /**
     * process header line for column mapping.
     * @param header header line.
     * @return map of column to index.
     */

    public static Map<BookData, Integer> processHeader(String header){
        Map<BookData, Integer> columnMap = new HashMap<>();
        String[] columns = header.split(DELIMITER);
        for(int i = 0; i<columns.length; i++){
            try{
                BookData col = BookData.fromColumnName(columns[i]);
                columnMap.put(col, i);
            }catch(IllegalArgumentException e){
                System.out.println("Error processing column" + columns[i]);
            }
        }
        return columnMap;
    }
}
