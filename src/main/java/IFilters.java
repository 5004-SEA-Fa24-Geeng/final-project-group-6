import model.IBook;

public interface IFilters {
    /**
     * Filters the books.
     * @param book
     * @param column
     * @param op
     * @param value
     * @return
     */
    public boolean filter(IBook book, BookDataSabrina column, Operations op, String value);

    /**
     * Filter based on string. Figure out if we are using a similar filtering system as 5-6?
     * @param bookData
     * @param op
     * @param value
     * @return
     */
    public boolean filterString(String bookData, Operations op, String value);

    /**
     * Filter based on int.
     * @param bookData
     * @param op
     * @param value
     * @return
     */
    public boolean filterInteger(int bookData, Operations op, String value);
}
