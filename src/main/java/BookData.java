/**
 * Enum to represent the columns in the book data.
 */
public enum BookData {
    /**
     * Enums to filter by
     */
    CATEGORY("Category"), AUTHOR("author"), TITLE("title"), ISBN("ISBN");

    private final String columnName;

    /**
     * The constructor for BookData
     * @param columnName The name of the column in the CSV file.
     */
    BookData(String columnName) {
        this.columnName = columnName;
    }

    /**
     * The getter for the column name.
     * @return The name of the column in the CSV file.
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Gets the enum from the column name.
     * @param columnName The name of the column in the CSV file.
     * @return the enum that matches the column name.
     */
    public static BookData fromColumnName(String columnName) {
        for (BookData bookData : BookData.values()) {
            if (bookData.getColumnName().equals(columnName)) {
                return bookData;
            }
        }
        throw new IllegalArgumentException("Invalid column name: " + columnName);
    }

    public static BookData fromString(String name) {
        for (BookData bookData : BookData.values()) {
            if (bookData.getColumnName().equalsIgnoreCase(name) || bookData.name().equalsIgnoreCase(name)) {
                return bookData;
            }
        }
        throw new IllegalArgumentException("Invalid column name: " + name);
    }
}
