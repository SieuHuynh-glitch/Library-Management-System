/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package library_management;

import java.util.Scanner;

/**
 *
 * @author MYPC
 */
public class Library_Management {

    Scanner sc = new Scanner(System.in);

    private String[] idbook = new String[1000];
    private String[] titles = new String[1000];
    private String[] authors = new String[1000];
    private String[] genres = new String[1000];
    private int[] publicationYears = new int[1000];
    private int[] quantities = new int[1000];
    private int bookCount = 0;

    private String[] memberIds = new String[1000];
    private String[] memberNames = new String[1000];
    private String[] memberPhones = new String[1000];
    private String[] memberEmails = new String[1000];
    private int memberCount = 0;

    private int borrowCount = 0;
    private String[] borrowMemberIds = new String[1000];
    private String[] borrowBookIds = new String[1000];
    private String[] borrowDates = new String[1000];
    private String[] dueDates = new String[1000];
    private boolean[] isReturned = new boolean[1000];

    private boolean[] isBookBorrowed = new boolean[1000];
    private int[] bookBorrowFrequencies = new int[1000];
    private int[] borrowedCounts = new int[1000];

    public void managebook() {
        boolean isRunning = true;
        while (isRunning == true) {
            System.out.println("----------- MANAGE BOOK -----------\n "
                    + "1. Add new book with details (ID, title, author, genre, publication year, quantity, …).\n"
                    + "2. Update book information.\n"
                    + "3. Remove a book (only if not currently borrowed).\n"
                    + "4. View all books.\n"
                    + "5. Search books by title, author, or genre.\n"
                    + "6. Return");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    addbook();
                    break;
                case 2:
                    updatebook();
                    break;
                case 3:
                    removebook();
                    break;
                case 4:
                    viewbook();
                    break;
                case 5:
                    searchbook();
                    break;
                case 6:
                    isRunning = false;
                    break;
                default:
                    break;

            }
        }

    }

    public void addbook() {

        System.out.println("- - - - - - - - ADD BOOK - - - - - - - -");

        System.out.print("Book ID: ");
        String id = sc.nextLine();

        System.out.print("Title: ");
        String title;
        title = sc.nextLine();

        System.out.print("Author: ");
        String author = sc.nextLine();

        System.out.print("Genre: ");
        String genre = sc.nextLine();

        System.out.print("Publication Year: ");
        int year = sc.nextInt();

        System.out.print("Quantity: ");
        int quantity = sc.nextInt();

        System.out.println("[1] Save [2] Cancel");
        System.out.print("Enter your choice: ");
        int option = sc.nextInt();
        switch (option) {

            case 1:
                idbook[bookCount] = id;
                titles[bookCount] = title;
                authors[bookCount] = author;
                genres[bookCount] = genre;
                publicationYears[bookCount] = year;
                quantities[bookCount] = quantity;
                System.out.println("=> Book updated successfully");
                bookCount++;
                break;
            case 2:
                System.out.println("=> Operation cancelled.");
                break;
            default:
                break;

        }
        sc.nextLine();
        System.out.print("Press ENTER to return to menu...");
        sc.nextLine();
    }

    public void updatebook() {
        System.out.println("\n- - - - - - - - UPDATE BOOK - - - - - - - -");

        System.out.print("Enter Book ID: ");
        String id = sc.nextLine();
        int index = -1;
        for (int i = 0; i < bookCount; i++) {
            if (idbook[i].equalsIgnoreCase(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Fail: Book not found!");
            return;
        }

        System.out.println("Current Information:");
        System.out.println("Title: " + titles[index]);
        System.out.println("Author: " + authors[index]);
        System.out.println("Genre: " + genres[index]);
        System.out.println("Publication Year: " + publicationYears[index]);
        System.out.println("Quantity: " + quantities[index]);
        System.out.print("Enter new Quantity : ");
        int newQty = sc.nextInt();

        System.out.println("[1] Save [2] Cancel");
        System.out.print("Enter your choice: ");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                quantities[index] = newQty;
                System.out.println("=> Book updated successfully!");
                break;
            case 2:
                System.out.println("=> Operation cancelled.");
                break;
            default:
                break;
        }
        sc.nextLine();
        System.out.print("Press ENTER to return to menu...");
        sc.nextLine();
    }

    public void removebook() {
        System.out.println("\n- - - - - - - - REMOVE BOOK - - - - - - - -");

        System.out.print("Enter Book ID: ");
        String id = sc.nextLine();
        int index = -1;
        for (int i = 0; i < bookCount; i++) {
            if (idbook[i].equalsIgnoreCase(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Fail: Book not found!");
            return;
        }

        boolean isCurrentlyBorrowed = false;
        for (int j = 0; j < borrowCount; j++) {
            if (borrowBookIds[j].equalsIgnoreCase(id) && !isReturned[j]) {
                isCurrentlyBorrowed = true;
                break;
            }
        }

        if (isCurrentlyBorrowed) {
            System.out.println("Fail: Cannot remove. This book is currently borrowed.");
            return;
        }

        System.out.println("Current Information:");
        System.out.println("Title: " + titles[index]);
        System.out.println("Author: " + authors[index]);
        System.out.println("Genre: " + genres[index]);
        System.out.println("Publication Year: " + publicationYears[index]);
        System.out.println("Quantity: " + quantities[index]);

        System.out.println("[1] Remove [2] Cancel");
        System.out.print("Enter your choice: ");
        int option = sc.nextInt();
        switch (option) {
            case 1:

                for (int i = index; i < bookCount - 1; i++) {
                    idbook[i] = idbook[i + 1];
                    titles[i] = titles[i + 1];
                    authors[i] = authors[i + 1];
                    genres[i] = genres[i + 1];
                    publicationYears[i] = publicationYears[i + 1];
                    quantities[i] = quantities[i + 1];

                    isBookBorrowed[i] = isBookBorrowed[i + 1];

                    bookBorrowFrequencies[i] = bookBorrowFrequencies[i + 1];
                }
                bookCount--; // Giảm số lượng sách
                System.out.println("=> Book removed successfully!");
                break;
            case 2:
                System.out.println("=> Operation cancelled.");
                break;
            default:
                break;
        }
        sc.nextLine();
        System.out.print("Press ENTER to return to menu...");
        sc.nextLine();
    }

    public void viewbook() {
        System.out.println("\n- - - - - - - - BOOK LIST - - - - - - - -");
        System.out.printf("%-5s %-25s %-20s %-15s %-5s %-3s\n", "ID", "Title", "Author", "Genre", "Year", "Qty");
        System.out.println("----------------------------------------------------------------------------------");
        for (int i = 0; i < bookCount; i++) {
            System.out.printf("%-5s %-25s %-20s %-15s %-5d %-3d\n",
                    idbook[i], titles[i], authors[i], genres[i], publicationYears[i], quantities[i]);
        }
        System.out.print("Press ENTER to return to menu...");
        sc.nextLine();
    }

    public void searchbook() {
        System.out.println("\n- - - - - - - - SEARCH BOOK - - - - - - - -");
        System.out.print("Enter keyword (Title, Author, or Genre): ");
        String keyword = sc.nextLine().trim().toLowerCase();

        boolean isFound = false;

        System.out.printf("%-5s %-25s %-20s %-15s %-5s %-3s\n", "ID", "Title", "Author", "Genre", "Year", "Qty");
        System.out.println("----------------------------------------------------------------------------------");

        for (int i = 0; i < bookCount; i++) {
            String currentTitle = titles[i].toLowerCase();
            String currentAuthor = authors[i].toLowerCase();
            String currentGenre = genres[i].toLowerCase();

            if (currentTitle.contains(keyword) || currentAuthor.contains(keyword) || currentGenre.contains(keyword)) {
                System.out.printf("%-5s %-25s %-20s %-15s %-5d %-3d\n",
                        idbook[i], titles[i], authors[i], genres[i], publicationYears[i], quantities[i]);
                isFound = true;
            }
        }

        if (!isFound) {
            System.out.println("No books matched your search criteria.");
        }
        System.out.print("Press ENTER to return to menu...");
        sc.nextLine();
    }

    public void managemember() {
        boolean isRunning = true;
        while (isRunning == true) {
            System.out.println("----------- MANAGE MEMBER -----------\n " + "1. Add new member (ID, name, phone, email, …).\n"
                    + "2. Update member information.\n"
                    + "3. Remove a member (only if no outstanding borrowed books).\n"
                    + "4. View all members.\n"
                    + "5. Search members by name or ID.\n"
                    + "6. Return");
            System.out.print("Choose an option: ");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    addmember();
                    break;
                case 2:
                    updatemember();
                    break;
                case 3:
                    removemember();
                    break;
                case 4:
                    viewmember();
                    break;
                case 5:
                    searchmember();
                    break;
                case 6:
                    isRunning = false;
                    break;
                default:
                    break;

            }
        }

    }

    public void addmember() {
        System.out.println("\n--- ADD NEW MEMBER ---");
        System.out.print("Enter Member ID: ");
        String id = sc.nextLine();

        boolean isDuplicate = false;
        for (int i = 0; i < memberCount; i++) {
            if (memberIds[i].equalsIgnoreCase(id)) {
                isDuplicate = true;
                break;
            }
        }

        if (isDuplicate) {
            System.out.println("Fail: Member ID already exists!");
            return;

        }
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.println("[1] Confirm [2] Cancel");
        System.out.print("Enter your choice: ");
        int option = sc.nextInt();
        switch (option) {
            case 1:

                memberIds[memberCount] = id;
                memberNames[memberCount] = name;
                memberPhones[memberCount] = phone;
                memberEmails[memberCount] = email;

                memberCount++;

                System.out.println("=> Book saved successfully!");
                break;
            case 2:
                System.out.println("=> Operation cancelled.");
                break;
            default:
                break;

        }
        sc.nextLine();
        System.out.print("Press ENTER to return to menu...");
        sc.nextLine();
    }

    public void updatemember() {
        int indexToUpdate = -1;
        System.out.print("Enter Member ID: ");
        String id = sc.nextLine();
        for (int i = 0; i < memberCount; i++) {
            if (memberIds[i].equalsIgnoreCase(id)) {
                indexToUpdate = i;
                break;
            }
        }

        if (indexToUpdate == -1) {
            System.out.println("Fail: Member not found!");
            return;
        }

        System.out.println("Current Information:");
        System.out.println("Name: " + memberNames[indexToUpdate]);
        System.out.println("Phone: " + memberPhones[indexToUpdate]);
        System.out.println("Email: " + memberEmails[indexToUpdate]);

        System.out.println("\nEnter new information (leave blank to skip):");

        System.out.print("New Name: ");
        String newName = sc.nextLine();

        if (!newName.isEmpty()) {
            memberNames[indexToUpdate] = newName;
        }

        System.out.print("New Phone: ");
        String newPhone = sc.nextLine();
        if (!newPhone.isEmpty()) {
            memberPhones[indexToUpdate] = newPhone;
        }

        System.out.print("New Email: ");
        String newEmail = sc.nextLine();
        System.out.println("[1] Confirm [2] Cancel");
        System.out.print("Enter your choice: ");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                if (!newEmail.isEmpty()) {
                    memberEmails[indexToUpdate] = newEmail;
                }

                System.out.println("=> Book saved successfully!");
                break;
            case 2:
                System.out.println("=> Operation cancelled.");
                break;
            default:
                break;

        }
        sc.nextLine();
        System.out.print("Press ENTER to return to menu...");
        sc.nextLine();
    }

    public void removemember() {
        System.out.println("\n--- REMOVE MEMBER ---");
        System.out.print("Enter Member ID to remove: ");
        String id = sc.nextLine();

        int indexToRemove = -1;

        for (int i = 0; i < memberCount; i++) {
            if (memberIds[i].equalsIgnoreCase(id)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            System.out.println("Fail: Member not found!");
            return;
        }
        boolean hasOutstandingBooks = false;
        for (int j = 0; j < borrowCount; j++) {
            if (borrowMemberIds[j].equalsIgnoreCase(id) && !isReturned[j]) {
                hasOutstandingBooks = true;
                break;
            }
        }

        if (hasOutstandingBooks) {
            System.out.println("Fail: Cannot remove. Member has outstanding borrowed books.");
            return;
        }
        System.out.println("[1] Confirm [2] Cancel");
        System.out.print("Enter your choice: ");
        int option = sc.nextInt();
        switch (option) {
            case 1:

                for (int i = indexToRemove; i < memberCount - 1; i++) {
                    memberIds[i] = memberIds[i + 1];
                    memberNames[i] = memberNames[i + 1];
                    memberPhones[i] = memberPhones[i + 1];
                    memberEmails[i] = memberEmails[i + 1];
                }

                memberCount--;

                System.out.println("=> Book saved successfully!");
                break;
            case 2:
                System.out.println("=> Operation cancelled.");
                break;
            default:
                break;

        }
        sc.nextLine();
        System.out.print("Press ENTER to return to menu...");
        sc.nextLine();
    }

    public void viewmember() {
        System.out.println("\n--- MEMBER LIST ---");

        for (int i = 0; i < memberCount; i++) {
            System.out.printf("Member %d: ID: %s, Name: %s, Phone: %s, Email: %s\n",
                    (i + 1), memberIds[i], memberNames[i], memberPhones[i], memberEmails[i]);
        }
        System.out.print("Press ENTER to return to menu...");
        sc.nextLine();
    }

    public void searchmember() {
        System.out.println("\n--- SEARCH MEMBERS ---");
        System.out.print("Enter exact Name or ID to search: ");

        String keyword = sc.nextLine();

        boolean isFound = false;

        System.out.println("\n--- SEARCH RESULTS ---");
        for (int i = 0; i < memberCount; i++) {

            if (memberIds[i].equalsIgnoreCase(keyword) || memberNames[i].equalsIgnoreCase(keyword)) {

                System.out.printf("Member %d: ID: %s, Name: %s, Phone: %s, Email: %s\n",
                        (i + 1), memberIds[i], memberNames[i], memberPhones[i], memberEmails[i]);

                isFound = true;
            }
        }

        if (!isFound) {
            System.out.println("No matching members found.");
        }
    }

    public void borrowing_returning() {
        boolean isRunning = true;
        while (isRunning == true) {
            System.out.println("----------- BORROW/RETURN -----------\n " + "1. Borrow a book (record book ID, member ID, borrow date).\n"
                    + "2. Return a book (record return date, calculate fine if overdue).\n"
                    + "3. View all borrowed books (currently out).\n"
                    + "4. View borrowing history for a specific member.\n"
                    + "5. Return");
            System.out.print("Choose an option: ");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    borrow();
                    break;
                case 2:
                    returnbook();
                    break;
                case 3:
                    viewborrowbook();
                    break;
                case 4:
                    viewhistory();
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    break;

            }

        }

    }

    public void borrow() {
        System.out.println("\n--- BORROW BOOK ---");
        System.out.print("Member ID: ");
        String memberId = sc.nextLine();

        int memberIndex = -1;
        for (int i = 0; i < memberCount; i++) {
            if (memberIds[i].equalsIgnoreCase(memberId)) {
                memberIndex = i;
                break;
            }
        }
        if (memberIndex == -1) {
            System.out.println("Fail: Member ID does not exist!");
            return;
        }

        System.out.print("Book ID: ");
        String bookId = sc.nextLine();

        int bookIndex = -1;

        for (int i = 0; i < bookCount; i++) {
            if (idbook[i].equalsIgnoreCase(bookId)) {
                bookIndex = i;
                break;
            }
        }
        if (bookIndex == -1) {
            System.out.println("Fail: Book ID does not exist!");
            return;
        }

        if (quantities[bookIndex] <= 0) {
            System.out.println("Fail: Book is currently out of stock!");
            return;
        }

        int currentlyBorrowed = 0;
        for (int i = 0; i < borrowCount; i++) {
            if (borrowMemberIds[i].equalsIgnoreCase(memberId) && !isReturned[i]) {
                currentlyBorrowed++;
            } else {
            }
        }
        if (currentlyBorrowed >= 3) {
            System.out.println("Fail: Member has reached the maximum borrowing limit (3 books).");
            return;
        }

        System.out.print("Borrow Date (DD/MM/YYYY): ");
        String borrowDate = sc.nextLine();
        System.out.print("Due Date (YYYY-MM-DD): ");
        String dueDate = sc.nextLine();
        System.out.println("[1] Confirm [2] Cancel");
        System.out.print("Enter your choice: ");
        int option = sc.nextInt();
        switch (option) {
            case 1:

                borrowMemberIds[borrowCount] = memberIds[memberIndex];
                borrowBookIds[borrowCount] = idbook[bookIndex];
                borrowDates[borrowCount] = borrowDate;
                dueDates[borrowCount] = dueDate;
                isReturned[borrowCount] = false;

                quantities[bookIndex]--;
                borrowCount++;
                isBookBorrowed[bookIndex] = true;
                bookBorrowFrequencies[bookIndex]++;
                borrowedCounts[memberIndex]++;

                System.out.printf("Success: Book '%s' borrowed by '%s' successfully.\n", titles[bookIndex], memberNames[memberIndex]);
                break;
            case 2:
                System.out.println("=> Operation cancelled.");
                break;
            default:
                break;

        }
        sc.nextLine();
        System.out.print("Press ENTER to return to menu...");
        sc.nextLine();
    }

    public void returnbook() {
        System.out.println("\n--- RETURN BOOK ---");
        System.out.print("Member ID: ");
        String memberId = sc.nextLine();
        System.out.print("Book ID: ");
        String bookId = sc.nextLine();

        int transactionIndex = -1;
        for (int i = 0; i < borrowCount; i++) {
            if (borrowMemberIds[i].equalsIgnoreCase(memberId)
                    && borrowBookIds[i].equalsIgnoreCase(bookId)
                    && !isReturned[i]) {
                transactionIndex = i;
                break;
            }
        }

        if (transactionIndex == -1) {
            System.out.println("Fail: No active borrowing record found for this member and book.");
            return;
        }

        System.out.print("Return Date (DD/MM/YYYY): ");
        String returnDate = sc.nextLine();

        System.out.print("Enter numbers of days overdue (Enter 0 if not overdue): ");
        int daysOverdue = sc.nextInt();
        sc.nextLine();

        isReturned[transactionIndex] = true;

        int bookIndex = -1;
        for (int i = 0; i < bookCount; i++) {
            if (idbook[i].equalsIgnoreCase(bookId)) {
                bookIndex = i;
                break;
            }
        }
        if (bookIndex != -1) {
            quantities[bookIndex]++;
        }

        if (daysOverdue > 0) {
            long fine = (long) daysOverdue * 5000;
            System.out.printf("Success: Book returned. Overdue fine: %,d VND.\n", fine);
        } else {
            System.out.println("Success: Book returned successfully. No overdue fine.");
        }

        System.out.print("Press ENTER to return to menu...");
        sc.nextLine();
    }

    public void viewborrowbook() {
        System.out.println("\n--- CURRENTLY BORROWED BOOKS ---");
        boolean hasBorrowed = false;

        for (int i = 0; i < borrowCount; i++) {
            if (!isReturned[i]) { // Chỉ hiện những cuốn chưa trả
                System.out.printf("Member ID: %s, Book ID: %s, Borrow Date: %s\n",
                        borrowMemberIds[i], borrowBookIds[i], borrowDates[i]);
                hasBorrowed = true;
            }
        }

        if (!hasBorrowed) {
            System.out.println("No books are currently borrowed.");
        }

        System.out.print("Press ENTER to return to menu...");
        sc.nextLine();

    }

    public void viewhistory() {

        System.out.println("\n--- CURRENTLY BORROWED BOOKS ---");
        boolean hasBorrowed = false;

        for (int i = 0; i < borrowCount; i++) {
            if (!isReturned[i]) {
                System.out.printf("Member ID: %s, Book ID: %s, Borrow Date: %s\n",
                        borrowMemberIds[i], borrowBookIds[i], borrowDates[i]);
                hasBorrowed = true;
            }
        }

        if (!hasBorrowed) {
            System.out.println("No books are currently borrowed.");
        }
        System.out.print("Press ENTER to return to menu...");
        sc.nextLine();
    }

    public void report() {
        boolean isRunning = true;
        while (isRunning == true) {
            System.out.println("\n----------- REPORT -----------");
            System.out.println("1. Generate a list of all currently borrowed books.\n"
                    + "2. Generate a list of overdue books.\n"
                    + "3. List most popular books (based on borrowing frequency).\n"
                    + "4. List members with the most borrowings.\n"
                    + "5. Return");
            System.out.print("Choose an option: ");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("\n--- CURRENTLY BORROWED BOOKS ---");
                    if (bookCount == 0) {
                        System.out.println("No books data.");
                    } else {
                        boolean hasBorrowed = false;
                        for (int i = 0; i < bookCount; i++) {
                            if (isBookBorrowed[i]) {
                                System.out.println("- " + titles[i]);
                                hasBorrowed = true;
                            }
                        }
                        if (!hasBorrowed) {
                            System.out.println("No books are currently borrowed.");
                        }
                    }
                    System.out.print("Press ENTER to return to menu...");
                    sc.nextLine();
                    break;

                case 2:

                    System.out.println("\n--- OVERDUE BOOKS ---");
                    if (borrowCount == 0) {
                        System.out.println("No borrowing records.");
                    } else {
                        System.out.print("Enter Today's Date (YYYY-MM-DD) to check overdue: ");
                        String today = sc.nextLine();
                        boolean hasOverdue = false;

                        for (int i = 0; i < borrowCount; i++) {

                            if (!isReturned[i] && dueDates[i].compareTo(today) < 0) {

                                String overdueBookTitle = "Unknown Book";
                                for (int j = 0; j < bookCount; j++) {
                                    if (idbook[j].equalsIgnoreCase(borrowBookIds[i])) {
                                        overdueBookTitle = titles[j];
                                        break;
                                    }
                                }

                                String borrowerName = "Unknown Member";
                                for (int k = 0; k < memberCount; k++) {
                                    if (memberIds[k].equalsIgnoreCase(borrowMemberIds[i])) {
                                        borrowerName = memberNames[k];
                                        break;
                                    }
                                }

                                System.out.printf("- Book: %s | Borrower: %s | Due Date: %s (Overdue)\n",
                                        overdueBookTitle, borrowerName, dueDates[i]);
                                hasOverdue = true;
                            }
                        }
                        if (!hasOverdue) {
                            System.out.println("Great! No overdue books found.");
                        }
                    }
                    System.out.print("Press ENTER to return to menu...");
                    sc.nextLine();
                    break;

                case 3:
                    System.out.println("\n--- MOST POPULAR BOOKS ---");
                    if (bookCount == 0) {
                        System.out.println("No books data.");
                        break;
                    }
                    int maxBookBorrow = bookBorrowFrequencies[0];
                    for (int i = 1; i < bookCount; i++) {
                        if (bookBorrowFrequencies[i] > maxBookBorrow) {
                            maxBookBorrow = bookBorrowFrequencies[i];
                        }
                    }

                    if (maxBookBorrow == 0) {
                        System.out.println("No books have been borrowed yet.");
                    } else {
                        for (int i = 0; i < bookCount; i++) {
                            if (bookBorrowFrequencies[i] == maxBookBorrow) {
                                System.out.println("- " + titles[i] + " (Borrowed: " + bookBorrowFrequencies[i] + " times)");
                            }
                        }
                    }
                    System.out.print("Press ENTER to return to menu...");
                    sc.nextLine();
                    break;

                case 4:
                    System.out.println("\n--- MEMBERS WITH MOST BORROWINGS ---");
                    if (memberCount == 0) {
                        System.out.println("No members in the system yet!");
                        break;
                    }

                    int maxMemberBorrow = borrowedCounts[0];
                    for (int i = 1; i < memberCount; i++) {
                        if (borrowedCounts[i] > maxMemberBorrow) {
                            maxMemberBorrow = borrowedCounts[i];
                        }
                    }

                    if (maxMemberBorrow == 0) {
                        System.out.println("No member has borrowed any books yet.");
                    } else {
                        for (int i = 0; i < memberCount; i++) {
                            if (borrowedCounts[i] == maxMemberBorrow) {
                                System.out.println("- " + memberNames[i] + " (ID: " + memberIds[i] + ") - Total Borrowings: " + borrowedCounts[i]);
                            }
                        }
                    }
                    System.out.print("Press ENTER to return to menu...");
                    sc.nextLine();
                    break;

                case 5:
                    System.out.println("Returning to Main Menu...");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public void initData() {
        idbook[0] = "B001";
        titles[0] = "The Great Gatsby";
        authors[0] = "F. Scott Fitzgerald";
        genres[0] = "Classic";
        publicationYears[0] = 1925;
        quantities[0] = 7;
        idbook[1] = "B002";
        titles[1] = "1984";
        authors[1] = "George Orwell";
        genres[1] = "Dystopian";
        publicationYears[1] = 1949;
        quantities[1] = 3;
        idbook[2] = "B003";
        titles[2] = "To Kill a Mockingbird";
        authors[2] = "Harper Lee";
        genres[2] = "Fiction";
        publicationYears[2] = 1960;
        quantities[2] = 5;
        bookCount = 3;

        memberIds[0] = "A001";
        memberNames[0] = "Lê Đỗ Anh Khoa";
        memberPhones[0] = "0901234567";
        memberEmails[0] = "khoa.le@gmail.com";
        memberIds[1] = "A002";
        memberNames[1] = "Nguyen Van B";
        memberPhones[1] = "0987654321";
        memberEmails[1] = "abc@gmail.com";
        memberIds[2] = "A003";
        memberNames[2] = "Trần Thị C";
        memberPhones[2] = "0912233445";
        memberEmails[2] = "tranthic@gmail.com";
        memberCount = 3;
    }

    public void startMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.print("===========================================\n"
                    + "LIBRARY MANAGEMENT SYSTEM\n"
                    + "===========================================\n"
                    + "1. Manage Books\n"
                    + "2. Manage Members\n"
                    + "3. Borrowing/Returning\n"
                    + "4. Reports\n"
                    + "5. Exit\n"
                    + "-------------------------------------------\n"
                    + "Choose an option: ");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    managebook();
                    break;
                case 2:
                    managemember();
                    break;
                case 3:
                    borrowing_returning();
                    break;
                case 4:
                    report();
                    break;
                case 5:
                    System.out.println("Exiting the system...");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    public static void main(String[] args) {

        Library_Management app = new Library_Management();
        app.initData();
        app.startMenu();

    }

}
