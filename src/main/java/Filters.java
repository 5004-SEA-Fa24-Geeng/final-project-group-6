public final class Filters {
    /**
     * Constructor
     */
    private Filters() {}

    public static boolean filter(InsertBookClass book, BookData column, Operations op, String value) {
        switch (column) {
            case CATEGORY:
                //getCategory might not exist in our implementation or be different. Placeholder for now
                return filterString(book.getCategory(), op, value);
            case AUTHOR:
                return filterString(book.getAuthor(), op, value);
            case TITLE:
                return filterString(book.getTitle(), op, value);
            case ISBN:
                return filterInteger(book.getISBN(), op, value);
            default:
                return false;
        }
    }

    /**
     * Filter based on string. Figure out if we are using a similar filtering system as 5-6?
     * @param bookData
     * @param op
     * @param value
     * @return
     */
    public static boolean filterString(String bookData, Operations op, String value) {
        switch (op) {
            default:
                return false;
        }
    }

    /**
     * Filter based on int.
     * @param bookData
     * @param op
     * @param value
     * @return
     */
    public static boolean filterInteger(int bookData, Operations op, String value) {
        switch (op) {
            default:
                return false;
        }
    }
}
