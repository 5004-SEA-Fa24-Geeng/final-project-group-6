package model;

public enum BookData {

    NAME("bookName"),TITLE("bookTitle"), AUTHOR("authorName"), CATEGORY("category"), ISBN("ISBN"),
    STATUS("status"), TYPE("bookType");


    private final String columnName;

    BookData(String columnName){this.columnName = columnName;}

    /**
     * Getter for column Name.
     * @return column name in csv file.
     */
    public String getColumnName(){
        return columnName;
    }

    /**
     *
     * @param columnName name of column.
     * @return enum that matches column name.
     */
    public static BookData fromColumnName(String columnName){
        for(BookData col : BookData.values()){
            if(col.getColumnName().equalsIgnoreCase(columnName)){
                return col;
            }
        }
        throw new IllegalArgumentException("No column with name: " + columnName);
    }


    /**
     *
     * @param name enum name.
     * @return matching enum for name.
     */
    public static BookData fromString(String name){
        for(BookData book: BookData.values()){
            if(book.name().equalsIgnoreCase(name) || book.getColumnName().equalsIgnoreCase(name)){
                return book;
            }
        }
        throw new IllegalArgumentException("No column with name: " + name);
    }
}
