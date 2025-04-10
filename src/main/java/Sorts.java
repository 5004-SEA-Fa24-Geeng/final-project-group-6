import java.util.Comparator;

public final class Sorts {
    /**
     * Constructor for Sorts.
     */
    private Sorts() {}

    /**
     * Sorts the books according to the column (either by title or author) and order (ascending or descending).
     * @param bookData The column name.
     * @param ascending In ascending or descending order.
     * @return A comparator to sort the books.
     */
    public static Comparator<InsertBookClass> getSorting(BookDataSabrina bookData, boolean ascending) {
        switch (bookData) {
            case TITLE:
                return (o1, o2) -> {
                    int compare = o1.getTitle().toLowerCase().compareTo(o2.getTitle().toLowerCase());
                    return ascending ? compare : -compare;
                };
            case AUTHOR:
                return (o1, o2) -> {
                    int compare = o1.getAuthor().toLowerCase().compareTo(o2.getAuthor().toLowerCase());
                    return ascending ? compare : -compare;
                };
            default:
                throw new IllegalArgumentException("Invalid sort");
        }
    }
}
