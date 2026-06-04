package oop_project;

import java.util.Scanner;

public class Report {
   
    private Scanner sc = new Scanner(System.in);
    private Book[] books;
    private int bookCount;
    private Member[] members;
    private int memberCount;
    private BorrowRecord[] records;
    private int borrowCount;

    public void setData(Book[] books, int bookCount, Member[] members, int memberCount, BorrowRecord[] records, int borrowCount) {
        this.books = books;
        this.bookCount = bookCount;
        this.members = members;
        this.memberCount = memberCount;
        this.records = records;
        this.borrowCount = borrowCount;
    }

    public void showMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n----------- REPORT -----------");
            System.out.println("1. Generate a list of all currently borrowed books.\n"
                    + "2. Generate a list of overdue books.\n"
                    + "3. List most popular books (based on borrowing frequency).\n"
                    + "4. List members with the most borrowings.\n"
                    + "5. Return");
            System.out.print("Choose an option: ");
            int option = this.sc.nextInt();
            this.sc.nextLine();

            switch (option) {
                case 1:
                    this.viewCurrentlyBorrowed();
                    break;
                case 2:
                    this.viewOverdueBooks();
                    break;
                case 3:
                    this.viewPopularBooks();
                    break;
                case 4:
                    this.viewTopBorrowers();
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

    public void viewCurrentlyBorrowed() {
        System.out.println("\n--- CURRENTLY BORROWED BOOKS ---");
        if (this.bookCount == 0) {
            System.out.println("No books data.");
        } else {
            boolean hasBorrowed = false;
            for (int i = 0; i < this.bookCount; i++) {
                boolean isBorrowed = false;
                for (int j = 0; j < this.borrowCount; j++) {
                    if (this.records[j].getBook().getId().equals(this.books[i].getId()) && !this.records[j].isReturned()) {
                        isBorrowed = true;
                        break;
                    }
                }
                if (isBorrowed) {
                    System.out.println("- " + this.books[i].getTitle());
                    hasBorrowed = true;
                }
            }
            if (!hasBorrowed) {
                System.out.println("No books are currently borrowed.");
            }
        }
        System.out.print("Press ENTER to return to menu...");
        this.sc.nextLine();
    }

    public void viewOverdueBooks() {
        System.out.println("\n--- OVERDUE BOOKS ---");
        if (this.bookCount == 0) {
            System.out.println("No books data.");
        } else {
            System.out.print("Enter Today (YYYY-MM-DD) to check overdue: ");
            String today = this.sc.nextLine();
            boolean hasOverdue = false;
            for (int i = 0; i < this.borrowCount; i++) {
                if (!this.records[i].isReturned() && this.records[i].getDueDate().compareTo(today) < 0) {
                    System.out.println("- " + this.records[i].getBook().getTitle() + " (Borrowed by: " + this.records[i].getMember().getName() + ")");
                    hasOverdue = true;
                }
            }
            if (!hasOverdue) {
                System.out.println("No overdue books found.");
            }
        }
        System.out.print("Press ENTER to return to menu...");
        this.sc.nextLine();
    }

    public void viewPopularBooks() {
        System.out.println("\n--- MOST POPULAR BOOKS ---");
        if (this.bookCount == 0) {
            System.out.println("No books data.");
            return;
        }

        int maxBookBorrow = this.books[0].getBorrowFrequency();
        for (int i = 1; i < this.bookCount; i++) {
            if (this.books[i].getBorrowFrequency() > maxBookBorrow) {
                maxBookBorrow = this.books[i].getBorrowFrequency();
            }
        }

        if (maxBookBorrow == 0) {
            System.out.println("No books have been borrowed yet.");
        } else {
            for (int i = 0; i < this.bookCount; i++) {
                if (this.books[i].getBorrowFrequency() == maxBookBorrow) {
                    System.out.println("- " + this.books[i].getTitle() + " (Borrowed: " + maxBookBorrow + " times)");
                }
            }
        }
        System.out.print("Press ENTER to return to menu...");
        this.sc.nextLine();
    }

    public void viewTopBorrowers() {
        System.out.println("\n--- MEMBERS WITH MOST BORROWINGS ---");
        if (this.memberCount == 0) {
            System.out.println("No members in the system yet!");
            return;
        }

        int maxMemberBorrow = this.members[0].getBorrowedCount();
        for (int i = 1; i < this.memberCount; i++) {
            if (this.members[i].getBorrowedCount() > maxMemberBorrow) {
                maxMemberBorrow = this.members[i].getBorrowedCount();
            }
        }

        if (maxMemberBorrow == 0) {
            System.out.println("No member has borrowed any books yet.");
        } else {
            for (int i = 0; i < this.memberCount; i++) {
                if (this.members[i].getBorrowedCount() == maxMemberBorrow) {
                    System.out.println("- " + this.members[i].getName() + " (ID: " + this.members[i].getId() + ") - Total Borrowings: " + maxMemberBorrow);
                }
            }
        }
        System.out.print("Press ENTER to return to menu...");
        this.sc.nextLine();
    }
}