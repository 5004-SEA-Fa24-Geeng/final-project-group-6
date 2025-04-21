# Manual 

Welcome to the Libris App!

## Start the program by running the LibrisApp.java file.

![start](Screenshots/Start.jpg)

## Help

### help
“help” will show you options for commands

![help](Screenshots/Help.jpg)

### help filter
“help filter” will show you options involving the filter command

![help_filter](Screenshots/Help_filter.jpg)

### help list
“help list” will show you options involving the list command

![help_list](Screenshots/Help_list.jpg)

## Filter

### filter
“filter” will show all library books in sorted by author name.

![filter](Screenshots/Filter.jpg)

### filter \<field>~=\<value>
This will show all the books in the list that contain said value in its field.

The fields are as follows: “bookTitle”, “authorName”, “category”, or “isbn”.

![filter_contains](Screenshots/Filter_contains.jpg)


### filter \<field>==\<value>
This will show all the books that have equal fields to the value.

The fields are as follows: “bookTitle”, “authorName”, “category”, or “isbn”

![filter_equals](Screenshots/Filter_equals.jpg)


### filter \<field>~=\<value>::sort \<sortType> or filter \<field>==\<value>::sort \<sortType>

This is used to sort the filtered list by either book title or author in ascending order. To do this, add ::sort \<sortType> to your filter command. 

Options for sortType are: "bookTitle" or "authorName".

![filter_sort](Screenshots/Filter_sort.jpg)


### filter clear
This clears the filter.

![filter_clear](Screenshots/Filter_clear.jpg)

## List

### list
Shows all the items in your list.

![list](Screenshots/List.jpg)

### list add \<isbn>
This adds a book to your list using the ISBN number.

![list_add](Screenshots/List_add.jpg)

### list add all
This adds all filtered books to your list.

![list_add_all](Screenshots/List_add_all.jpg)

### list remove \<isbn>
This removes the book from your list using the ISBN number.

![list_remove](Screenshots/List_remove.jpg)

### list clear
This removes all books in the list.

![list_clear](Screenshots/List_clear.jpg)

### list checkout \<isbn>
This checks out a book in your list. If no ISBN is provided, it checks out all available books in the list. Checked out books get added to the CSV file “output/checkoutBook.csv” and update the “Library.csv” file. If the book is an illegal book, a password is required to check it out. The password is “secret”. If you are checking out all book in the list and one is illegal, the user will be prompted for the password still.

![list_checkout_isbn](Screenshots/List_checkout_isbn.jpg)

Checkout all books:

![list_checkout_all](Screenshots/List_checkout_all.jpg)

Illegal book checkout:

![list_checkout_illegal](Screenshots/List_checkout_illegal.jpg)


### list checkin \<isbn>
This checks in a book. If no ISBN is provided, it checks in all the books in the list. This will update the Library.csv file.

![list_checkin_isbn](Screenshots/List_checkin_isbn.jpg)

Checkin all books:

![list_checkin_all](Screenshots/List_checkin_all.jpg)

### exit
"exit" terminates the program.

![exit](Screenshots/Exit.jpg)

