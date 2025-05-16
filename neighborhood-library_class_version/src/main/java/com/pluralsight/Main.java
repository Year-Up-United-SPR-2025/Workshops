package com.pluralsight;

import com.sun.tools.jconsole.JConsoleContext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    private static Console console = new Console();
    private static Book[] library = getPopulatedBooks();

    public static void main(String[] args) {

        showScreenHome();

    }

    private static void showScreenHome() {

        String homeScreenPrompt = "Welcome to the library!\n" +
                "Please select an option from the following:\n" +
                "    1 - Show Available Books\n" +
                "    2 - Show Checked Out Books\n" +
                "    0 -  Exit App\n" +
                "(1,2,0): ";

        int option;

        do {
            option = console.promptForInt(homeScreenPrompt);
            if (option == 1) {
                showScreenAvailableBooks();
            } else if (option == 2) {
                showScreenCheckedOutBooks();
            } else if (option == 0) {
                System.out.println("Exiting the library, have a nice day!");
            } else {
                System.out.println("Invalid entry, please try again!");
            }

        } while (option != 0);
    }

    private static void showScreenAvailableBooks() {

        // greet user to this menu
        System.out.println("Available Books:");

        // show all of the books that are currently available
        displayAvailableBooks();

        // ask if user wants to check one out.
        String userPrompt = "Select a option: \n" +
                "  Y - If you want to select a book to check out\n" +
                "  N - To go back to the home screen\n" +
                "(Y,N): ";

        String option;

        do {
            option = console.promptForString(userPrompt);
            if (option.equalsIgnoreCase("Y")) {
                // if yes - prompt for which book, and who will check it out then modify data to reflect current state of app
                showScreenCheckOutBookYes();
            } else if (!option.equalsIgnoreCase("N")) {
                System.out.print("Please choose from the available options!\n");
            }

        } while (!option.equalsIgnoreCase("N")); // keep asking until a no
        //on no, exit to main.
    }

    private static void showScreenCheckOutBookYes() {

        int bookId = console.promptForInt("Please enter the book Id: ");
        String name = console.promptForString("What is your name: ");

        Book theSelectedBook = getBookById(library, bookId);

        assert theSelectedBook != null;

        theSelectedBook.checkOut(name);

        System.out.printf("%s you have checkout out successfully.\n", name);
    }

    private static void displayAvailableBooks() {
        System.out.println(Book.getFormattedBookTextHeader());
        for (Book book : library) {
            if (!book.isCheckedOut()) {
                System.out.println(book.getFormattedBookText());
            }
        }
    }

    private static Book getBookById(Book[] books, int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    private static void showScreenCheckedOutBooks() {
        System.out.println("...todo checked out books here...");
    }


    private static Book[] getPopulatedBooks()  {

        try{
            FileReader fr = new FileReader("books.txt");
            BufferedReader reader = new BufferedReader(fr);

            Book[] booksTemp = new Book[1000];
            int size = 0;
            String dataString;

            while((dataString = reader.readLine()) != null){

                booksTemp[size] = getBookFromEncodedString(dataString);

                size++;

            }

            Book[] booksFinal = Arrays.copyOf(booksTemp, size);


            return booksFinal;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private static Book getBookFromEncodedString(String encodedBook){

        String[] temp = encodedBook.split(Pattern.quote("|"));

        int id = Integer.parseInt(temp[0]);
        String isbn = temp[1];
        String title = temp[2];

        Book result = new Book(id, isbn, title);
        return result;
    }

}