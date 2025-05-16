package com.pluralsight;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Book[] library = getPopulatedLibrary();
    private static Book[] books;

    public static void main(String[] args) {
        ShowScreenHome();
    }

    private static void ShowScreenHome() {

        String homeScreenPrompt = "Welcome to the library\n" +
                "Please select an option from the following\n" +
                " 1- Show Available Books\n  " +
                "  2 - Show Checked Out Books\n  " +
                "  0- Exit App\n  " +
                "(1,2,0):";

        int option;

        do {
            System.out.print(homeScreenPrompt);
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                ShowScreenAvailableBooks();
            } else if (option == 2) {
                ShowScreenCheckedOutBooks();
            } else if (option == 0) {
                System.out.println("Exiting the library have a nice day");
            } else {
                System.out.println("Not a valid option please try again");
            }
        } while (option != 0);
        System.exit(0);

    }

    private static void ShowScreenAvailableBooks() {
        System.out.println("\nAvailable Books:");

        boolean availbooks = false;
        for (Book book : library) {
            if (!book.isCheckedOut()) {
                System.out.println(" ID: " + book.getId() + " | ISBN: " + book.getIsbn() + " | Title: " + book.getTitle());
                availbooks = true;
            }
        }

        if (!availbooks) {
            System.out.println("No books are currently checked out.\n");
            return;
        }

        System.out.println("\nEnter the Id of the book you want to check out, or press 0 to go back: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (id == 0) return;

        Book selectedBook = null;
        for (Book book : library) {
            if (book.getId() == id && !book.isCheckedOut()) {
                selectedBook = book;
                break;
            }
        }

        if (selectedBook != null) {
            System.out.print("Enter your name to check out the book: ");
            String name = scanner.nextLine();
            selectedBook.checkOut(name);
            System.out.println("Book \"" + selectedBook.getTitle() + "\" has been checked out\n");
        } else {
            System.out.println("Invalid book ID or the book is already checked out.\n");
        }

    }

    private static void ShowScreenCheckedOutBooks() {
        System.out.println("\nChecked out books:");

        boolean checkedOutBooks = false;
        for (Book book : library) {
            if (book.isCheckedOut()) {
                System.out.println(" ID: " + book.getId() + " | ISBN: " + book.getIsbn() + " | Title: " + book.getTitle());
                checkedOutBooks = true;
            }
        }
        if (!checkedOutBooks) {
            System.out.println("No books are currently checked out.\n");
            return;
        }

        System.out.print("\nEnter 'C' to check in a book or 'X' to return to the home screen: ");
        String input = scanner.nextLine().trim().toUpperCase();

        if (input.equals("C")) {
            System.out.print("Enter the ID of the book to check in: ");
            int checkInId = scanner.nextInt();
            scanner.nextLine(); // consume newline

            Book bookToCheckIn = null;
            for (Book book : library) {
                if (book.getId() == checkInId && book.isCheckedOut()) {
                    bookToCheckIn = book;
                    break;
                }
            }

            if (bookToCheckIn != null) {
                bookToCheckIn.checkIn();
                System.out.println("Book \"" + bookToCheckIn.getTitle() + "\" has been checked in successfully.\n");
            }
        }
    }


    private static Book[] getPopulatedLibrary() {

        Book[] Library = new Book[20];
        //book names id and isbn
        Library[0] = new Book(1, "9780061120084", "To Kill a Mockingbird");
        Library[1] = new Book(2, "9780451524935", "1984");
        Library[2] = new Book(3, "9780743273565", "The Great Gatsby");
        Library[3] = new Book(4, "9780141439518", "Pride and Prejudice");
        Library[4] = new Book(5, "9780316769488", "The Catcher in the Rye");
        Library[5] = new Book(6, "9780547928227", "The Hobbit");
        Library[6] = new Book(7, "9781451673319", "Fahrenheit 451");
        Library[7] = new Book(8, "9781503280786", "Moby-Dick");
        Library[8] = new Book(9, "9780141441146", "Jane Eyre");
        Library[9] = new Book(10, "9780060850524", "Brave New World");
        Library[10] = new Book(11, "9780451526342", "Animal Farm");
        Library[11] = new Book(12, "9780141439556", "Wuthering Heights");
        Library[12] = new Book(13, "9780544003415", "The Lord of the Rings");
        Library[13] = new Book(14, "9780066238500", "The Chronicles of Narnia");
        Library[14] = new Book(15, "9780439023528", "The Hunger Games");
        Library[15] = new Book(16, "9780590353427", "Harry Potter and the Sorcerer's Stone");
        Library[16] = new Book(17, "9780142424179", "The Fault in Our Stars");
        Library[17] = new Book(18, "9780062315006", "The Alchemist");
        Library[18] = new Book(19, "9780375842207", "The Book Thief");
        Library[19] = new Book(20, "9780544336261", "The Giver");

        return Library;
    }
}
