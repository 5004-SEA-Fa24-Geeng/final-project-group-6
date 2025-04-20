# Design Documents

You may have multiple design documents for this project. Place them all in this folder. File naming is up to you, but it should be clear what the document is about. At the bare minimum, you will want a pre/post UML diagram for the project.
Initial UML
```mermaid
---
title: Final Project Group 06 -  Initial design
---
classDiagram
direction BT
    class BookRegister {
        - In: Scanner
        - book: IBook 
        - output: OutputStream
        + start(): void
        + processHelp(): void
        + processCheckIn(): void
        + processCheckOut(): void
        
    }

    class IllegalBook {
        - bookName: String 
        - bookTitle: String
        - author: String
        - category: String
        + getBookName(): String
        + getBookTitle(): String
        + getAuthor(): String
        + getCategory(): String
        + getStatus(): String
        - bookReplacement(): Book
        + toString(): String
    }
    class StandardBook {
        - bookName: String 
        - bookTitle: String
        - author: String
        - category: String
        + getBookName(): String
        + getBookTitle(): String
        + getAuthor(): String
        + getCategory(): String
        + getISBN(): String
        + getStatus(): String
        

    }
    class IBook {
        getBookName(): String
        getBookTitle(): String
        getAuthor(): String
        getCategory(): String
        getISBN(): String
        getStatus(): String
        + toString(): String
    }
    class LibrisApp {
        + main(String[] args)
    }
    class DataHandler {
        - Map<BookData, List<String>> books
        - CheckInFile: String
        - CheckoutFile : String
        + Map<String, List<String>> loadBooks(File: String, column: String )
        + updateBookList(books: Map<BookData, List<String>> ,ISBN: String, currentStatus: String, updatedStatus: String)
        +checkoutBook(books: Map<BookData, List<String>>, ISBN: String)
    }
    class DataFilter {
        + DataFilter(books: Map<BookData, List<String>>)
        + DataFilter(books: Map<BookData, List<String>>, sort: DataSorts)
        + getBooks: Map<String, List<String>>
        + reset():void
        + getComparator(sort: DataSorts): Comparator<books>

    }
    class DataSorts {
        NameSort: class
        titleSort: class
        AuthorSort: class
        categorySort: class
    }
    class IFilter {
        filter(filter: String): Stream<Books>
        filter(filter: String, category: String): Stream<Books>
        reset(): void
    }
    class ISorts {
        compare(o1: book, o2 book)

    }
    class Operations {
        Operations(operator: String)
        + getOperatorFromStr(str: String): Operator
        + fromOperatior(oprator: String): Operator
        + getOperator(String: operator)
    }
    class BookLoader {
        loadBooksFromFile(filename: String)
        toBook(line: String, Map<BookData, List<String>>): books
        processHeader(heaader: String): Map<BookData, List<String>>
    }
    class BookController {
        - checkOuFile: String 
        - checkInFile: String
        + checkInBooks(books: Collection<IBook>, ISBN: String)
        + checkOutBooks(books: Collection<IBook>, ISBN: String)

    }

    class filters{
        filter(book: IBook, column: BookLoader, op: Operations, value: String)
        filterString()
        filterNum()
    }
    class bookData{
        - columnName: String
        + getColumnName(): String
        + fromColumnName(columnName: String): bookData
        + fromString(name: String): bookData

    }
	<<enumeration>> Operations

    LibrisApp --> BookRegister
    BookController --> BookRegister 
    IllegalBook --> BookController
    StandardBook --> BookController
    IBook ..|> IllegalBook
    IBook ..|> StandardBook
    DataHandler --> BookRegister
    DataFilter --> DataHandler
    DataSorts --> DataHandler
    IFilter ..|> DataFilter
    ISorts ..|> DataSorts
    filters --> DataFilter
    bookData --> BookLoader

```

Final UML
```mermaid
---
title: Final Project Group 06 -  Libris Design
---
classDiagram
direction BT
    class BookRegister {
        - in: Scanner
        - controller: IBookController
        - bookList: IBookList
        - SabrinaPlanner: IPlanner
        - lastFilteredBooks: List<IBook>
        - current: Scanner
        + BookRegister(IBookList bookList, IPlanner planner, IBookController controller)
        + start(): void
        - processHelp(): void
        - processFilter(): void
        - printFilterStream(Stream<IBook> books, BookData sortON): void
        - processList(): void
        - printCurrentList(): void
        - remainder(): String
        + processCheckIn(isbn: String): void
        + processCheckOut(isbn: String): void
        - nextCommand(): ConsoleText
        - getInput(String format, Object... args): String
        - printOutput(String format, Object... output): void
    }

    class IllegalBook {
        - PASSWORD = "secret": String
        + IllegalBook(String isbn, String bookTitle, String author, String category, String status)
        + getReplacement(): StandardBook
        + validatePassword(String password): Boolean
    }
    class StandardBook {
        StandardBook(String isbn, String bookTitle, String author, String category, String status)
        
    }

    class IBook {
        getBookType(): String
        getBookTitle(): String
        getAuthor(): String
        getCategory(): String
        getISBN(): String
        getStatus(): String
        toString(): String
        toStringWithInfo(BookData sortON): String
        compareTo(IBook other): int
        setStatus(String status): void
    }

    class AbstractBook{
        - String isbn
        - String bookTitle
        - String author
        - String category
        - String bookType
        - String status
        + AbstractBook(String isbn, String bookType, String bookTitle, String author, String category, String status)
        + getBookType(): String
        + getBookTitle(): String
        + getAuthor(): String
        + getCategory(): String
        + getISBN(): String
        + getStatus(): String
        + setStatus(String status):void
        + toString(): String
        + toStringWithInfo(BookData col): String
        + equals(Object obj): boolean
        + compareTo(IBook other): int
        + hashcode(Object obj): int

    }

    class LibrisApp {
        - DEFAULT_COLLECTION = "/Library.csv": String
        - LibrisApp()
        + main(String[] args)
    }
    
    class IFilters {
        + filter(IBook book, BookData column, Operations op, String value) : boolean
        + filterString(String BookData, Operations op, String value) : boolean
    }
    class ISorts {
        + getSorting(BookDataSabrina BookData, boolean ascending) : Comparator<IBook>
    }

    class Sorts{
        + getSorting(BookDataSabrina bookData, boolean ascending) : Comparator<IBook>
    }

    class IBookList{
        getBooks(): List<IBook>
        addToList(String str, Stream<IBook> filtered): void
        clear(): void
        updateLibrary(String filename, String isbnToUpdate, String newStatus): void
        updateCheckoutFile(String filename, IBook book): void
        removeFromList(String str): void
        count(): int

    }

    class BookList{
        - books: Set<IBook>
        + BookList()
        + getBooks(): List<IBook>
        + clear(): void
        + addToList(String input, Stream<IBook> filtered): void
        + updateLibrary(String filename, String isbnToUpdate, String newStatus): void
        + updateCheckoutFile(String filename, IBook book): void
        + removeFromList(String input): void
        + count(): int
    }

    class Operations {
        EQUALS
        CONTAINS
        - operator: String
        Operations(String operator)
        + getOperatorFromStr(String str): Operations
        + fromOperator(String operator): Operations
        + getOperator(String operator)
    }
    class BookLoader {
        - DELIMITER = ",": String
        - BookLoader()
        + loadBooksFromFile(String filename): Set<IBook>
        + loadBooksFromFile(Path path): Set<IBook>
        + toBook(String line, Map<BookData, List<String>>): IBook
        - toStandardBook(String[] columns, Map<BookData, Integer> columnMap): IBook
        - toIllegalBook(String[] columns, Map<BookData, Integer> columnMap): IBook
        + processHeader(String header): Map<BookData, List<String>>
    }

    class IBookController{
        checkOutBooks(Collection<IBook> books, String ISBN, IBookList bookList): void
        checkInBooks(Collection<IBook> books, String ISBN, IBookList bookList): void
    }

    class BookController {
        + checkOutBooks(Collection<IBook> books, String ISBN, IBookList bookList): void
        + checkInBooks(Collection<IBook> books, String ISBN, IBookList bookList): void
    }

    class Filters{
        + filter(IBook book, BookData column, Operations op, String value) : boolean
        + filterString(String bookData, Operations op, String value) : boolean
    }
    class BookData{
        NAME
        TITLE
        AUTHOR
        CATEGORY
        ISBN
        STATUS
        TYPE
        - columnName: String
        BookData(String columnName)
        + getColumnName(): String
        + fromColumnName(columnName: String): BookData
        + fromString(name: String): BookData

    }

    class IPlanner{
        filter(String filter): Stream<IBook>
        filter(String filter, BookData sortOn): Stream<IBook>
        filter(String filter, BookData sortOn, boolean ascending): Stream<IBook>
    }

    class Planner{
        - Set<IBook> books
        + Planner(Set<IBook> books)
        + filter(String filter): Stream<IBook>
        - filterSingle(String filter, Stream<IBook> filteredBooks): Stream<IBook>
        + filter(String filter, BookData sortOn): Stream<IBook>
        + filter(String filter, BookData sortOn, boolean ascending): Stream<IBook>
    }

    class InputValidator{
        + isValidISBN(String isbn): boolean
    }

	<<enumeration>> Operations
    <<enumeration>> BookData
    <<interface>> IBookController
    <<interface>> IFilters
    <<interface>> IPlanner
    <<interface>> ISorts
    <<interface>> IBook
    <<interface>> IBookList


    BookController ..|> IBookController
    Filters ..|> IFilters
    Planner ..|> IPlanner
    Sorts ..|> ISorts
    AbstractBook ..|> IBook
    BookList ..|> IBookList

    IllegalBook --|> AbstractBook
    StandardBook --|> AbstractBook

    LibrisApp --> IBookController
    BookRegister --> IBookController

    IPlanner --> IFilters

    BookRegister --> IPlanner
    LibrisApp --> IPlanner

    IPlanner --> ISorts

    IFilters --> Operations
    IPlanner --> Operations

    IPlanner --> BookData
    BookLoader --> BookData
    BookRegister --> BookData
    IFilters --> BookData
    ISorts --> BookData

    LibrisApp --> BookLoader

    LibrisApp --> IBookList

    IBookController --> IBook
    IBookController --> IBookList
    IBookController --> IllegalBook

    IBookList --> IllegalBook
    BookLoader --> IllegalBook

    AbstractBook --> BookData


    LibrisApp --> BookRegister

    BookRegister --> IBookList
    BookRegister --> IBook

    BookRegister --> InputValidator
```