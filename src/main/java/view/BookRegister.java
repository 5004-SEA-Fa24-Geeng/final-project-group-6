package view;

import controller.*;
import model.*;
import util.InputValidator;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Stream;

public class BookRegister {
    private static Scanner in = new Scanner(System.in);
    private final IBookController controller;

    private final IBookList bookList;

    private final IPlanner SabrinaPlanner;

    private List<IBook> lastFilteredBooks = new ArrayList<>();



    /**
     * scanner to help with processing the command string.
     */
    private Scanner current;

    public BookRegister(IBookList bookList, IPlanner planner, IBookController controller) {
        this.controller = controller;
        this.bookList = bookList;
        this.SabrinaPlanner = planner;
    }

    /**
     * Start application.
     */
    public void start() {
        printOutput("%s%n", ConsoleText.WELCOME);
        ConsoleText ct = nextCommand();

        while (ct != ConsoleText.CMD_EXIT) {
            switch (ct) {
                case CMD_HELP:
                    processHelp();
                    break;
                case CMD_FILTER:
                    processFilter();
                    break;
                case CMD_LIST:
                    processList();
                    break;
                case INVALID:
                default:
                    printOutput("%s%n", ConsoleText.INVALID);
            }
            current.close();
            current = null;
            // get the next prompt
            ct = nextCommand();
        }

        printOutput("%s%n", ConsoleText.GOODBYE);
    }

    /**
     * Process the help command.
     */
    private void processHelp() {
        ConsoleText ct = ConsoleText.CMD_HELP;
        if (current.hasNext()) {
            ct = nextCommand();
        }
        switch (ct) {
            case CMD_FILTER:
                printOutput("%s%n", ConsoleText.FILTER_HELP);
                break;
            case CMD_LIST:
                printOutput("%s%n", ConsoleText.LIST_HELP);
                break;
            default:
                printOutput("%s%n", ConsoleText.HELP);
        }
    }

    /**
     * Process the filter command.
     */
    private void processFilter() {
        Stream<IBook> result = null;
        BookData sortON = BookData.NAME; // default

        if (current.hasNext()) {
            String filter = remainder();
            filter = filter.replaceAll("\\s", ""); // remove spaces
            filter = filter.toLowerCase(); // make it lower case
            if (filter.equalsIgnoreCase(ConsoleText.CMD_QUESTION.toString())) {
                printOutput("%s%n", ConsoleText.FILTER_HELP);
                return; // leave early.
            }
            if (filter.equalsIgnoreCase(ConsoleText.CMD_CLEAR.toString())) {
                printOutput("%s%n", ConsoleText.FILTERED_CLEAR);
                lastFilteredBooks = new ArrayList<>();
                return; // leave early.
            }

            if (filter.contains(ConsoleText.CMD_SORT_OPTION.toString())) {
                // break it up, figure out sort
                boolean ascending = true; // default
                String[] parts = filter.split(ConsoleText.CMD_SORT_OPTION.toString());
                if (parts.length == 2) {
                    String sort = parts[1];
                    try {
                        sortON = BookData.fromString(sort);
                    } catch (IllegalArgumentException e) {
                        printOutput("%s%n", ConsoleText.INVALID);
                        return; // leave early.
                    }
                }
                result = SabrinaPlanner.filter(parts[0], sortON, ascending);  // NOTICE: sortON and ascending are used here.
            } else {
                result = SabrinaPlanner.filter(filter); // default sort
            }
        } else {
            printOutput("%s%n", ConsoleText.NO_FILTER);
            result = SabrinaPlanner.filter("");
        }
        lastFilteredBooks = result != null ? result.toList() : new ArrayList<>();
        printFilterStream(lastFilteredBooks.stream(), sortON);
    }

    /**
     * Print the filtered stream of books.
     *
     * @param books  the stream of books to print.
     * @param sortON sortOn column based on book info.
     */
    private static void printFilterStream(Stream<IBook> books, BookData sortON) {
        int counter = 1;
        List<IBook> bookList = books != null ? books.toList() : Collections.emptyList();
        for (IBook book : bookList) {
            printOutput("%d: %s%n", counter++, book.toStringWithInfo(sortON));
        }
    }


    /**
     * Process the list command.
     */
    private void processList() {
        ConsoleText ct = ConsoleText.INVALID;
//        if (current.hasNext()) {
//
//            ct = nextCommand();
        if (current.hasNext()) {
            // consume the *next* part after "list"
            String next = current.next().trim();
            ct = ConsoleText.fromString(next);
            switch (ct) {
                case CMD_SHOW:
                    printCurrentList();
                    break;
                case CMD_CLEAR:
                    bookList.clear();
                    break;
                case CMD_ADD:
                    String toAdd = remainder().toLowerCase();
                    if (toAdd.isEmpty()) break;

                    if (toAdd.equals("all")) {
                        if (lastFilteredBooks == null || lastFilteredBooks.isEmpty()) {
                            System.out.println("No filtered books to add.");
                        } else {
                            for (IBook book : lastFilteredBooks) {
                                bookList.addToList(book.getISBN(), lastFilteredBooks.stream());
                            }
                            System.out.println("All filtered books added to list.");
                        }
                    } else {
                        try {
                            bookList.addToList(toAdd, SabrinaPlanner.filter(""));
                        } catch (IllegalArgumentException e) {
                            printOutput("%s %s%n", ConsoleText.INVALID_LIST, toAdd);
                        }
                    }
                    break;

                case CMD_REMOVE:
                    String remove = remainder().toLowerCase();
                    if (remove.isEmpty()) break;

                    if (remove.equals("all")) {
                        if (lastFilteredBooks == null || lastFilteredBooks.isEmpty()) {
                            System.out.println("No filtered books to remove.");
                        } else {
                            for (IBook book : lastFilteredBooks) {
                                bookList.removeFromList(book.getISBN());
                            }
                            printOutput("All filtered books removed from the list.%n");
                        }
                        break;
                    }

                    try {
                        bookList.removeFromList(remove);
                    } catch (IllegalArgumentException e) {
                        printOutput("%s %s%n", ConsoleText.INVALID_LIST, remove);
                    }
                    break;

                case CMD_CHECKOUT:
                    processCheckOut(remainder().trim());
                    break;

                case CMD_CHECKIN:
                    processCheckIn(remainder().trim());
                    break;
                case CMD_QUESTION:
                case CMD_HELP:
                    printOutput("%s%n", ConsoleText.LIST_HELP);
                    break;
                default:
                    printOutput("%s%n", ConsoleText.INVALID);
                    printOutput("%s%n", ConsoleText.LIST_HELP);
            }
        } else {
            printCurrentList();
        }
    }




    /**
     * Print the current list of books.
     */
    private void printCurrentList() {
        if (bookList.count() > 0) {
            int counter = 1;
            for (IBook book : bookList.getBooks()) {
                printOutput("%d: %s%n", counter++, book);
            }
        } else {
            System.out.println("No Books in current list.");
        }

    }


    /**
     * @return the remainder of the current line.
     */
    private String remainder() {
        return current != null && current.hasNext() ? current.nextLine().trim() : "";
    }


    public void processCheckIn(String isbn) {
        // Check if no ISBN was passed
        if (isbn == null || isbn.isEmpty()) {
            for (IBook book : bookList.getBooks()) {
                if (book.getStatus().equalsIgnoreCase("unavailable")) {
                    controller.checkInBooks(bookList.getBooks(), book.getISBN(), bookList);
                }
            }
            return;
        }

        // Otherwise, check in a single book
        if (!InputValidator.isValidISBN(isbn)) {
            System.out.println("Invalid ISBN format. Must be 10 or 13 digits.");
            return;
        }
        controller.checkInBooks(bookList.getBooks(), isbn, bookList);
    }

    public void processCheckOut(String isbn) {
        // Check if no ISBN was passed
        if (isbn == null || isbn.isEmpty()) {
            for (IBook book : bookList.getBooks()) {
                if (book.getStatus().equalsIgnoreCase("available")) {
                    controller.checkOutBooks(bookList.getBooks(), book.getISBN(), bookList);
                }
            }
            return;
        }

        // Otherwise, check out a single book
        if (!InputValidator.isValidISBN(isbn)) {
            System.out.println("Invalid ISBN format. Must be 10 or 13 digits.");
            return;
        }
        controller.checkOutBooks(bookList.getBooks(), isbn, bookList);
    }



    /**
     * Get the next command from the user.
     *
     * @return the next command.
     */
    private ConsoleText nextCommand() {
        if (current == null || !current.hasNext()) {
            String line = getInput("%s", ConsoleText.PROMPT);
            current = new Scanner(line.trim());
        }
        return ConsoleText.fromString(current.next());
    }

    /**
     * Gets input from the client.
     *
     * @param format the format string to print.
     * @param args   the arguments to the format string.
     * @return the input from the client as a string, one line at a time.
     */

    private static String getInput(String format, Object... args) {
        System.out.printf(format, args);
        if (in == null) {
            return "";
        }
        if (!in.hasNextLine()) {
            return "";
        }
        return in.nextLine();
    }

    /**
     * Prints output to the user.
     *
     * @param format the format string to print.
     * @param output the output to print (array to match the format).
     */
    private static void printOutput(String format, Object... output) {
        System.out.printf(format, output);
    }

    private enum ConsoleText {
        /**
         * various commands and text.
         */
        WELCOME, PROMPT, INVALID, HELP, CMD_SHOW, FILTER_HELP, FILTERED_CLEAR, NO_FILTER,

        CMD_SORT_OPTION, GOODBYE, LIST_HELP, INVALID_LIST,

        CMD_EXIT, CMD_HELP, CMD_QUESTION, CMD_FILTER, CMD_LIST,
        /**
         * commands specific to lists and filters.
         */
        CMD_ADD, CMD_REMOVE, CMD_CLEAR, CMD_CHECKOUT, CMD_CHECKIN;


        /**
         * load the files on class load.
         */
        private static final Properties CTEXT = new Properties();

        @Override
        public String toString() {
            return CTEXT.getProperty(this.name().toLowerCase());
        }

        /**
         * Get the enum from a string.
         *
         * @param str the string to convert to an enum.
         * @return the enum value.
         */
        public static ConsoleText fromString(String str) {
            for (ConsoleText ct : ConsoleText.values()) {
                if (ct.toString() != null && ct.toString().equalsIgnoreCase(str)) {
                    return ct;
                }
            }
            return ConsoleText.INVALID;
        }

        static {
            try {
                CTEXT.loadFromXML(BookRegister.class.getResourceAsStream("/console.properties"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
