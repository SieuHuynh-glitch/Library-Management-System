/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package librarymanagemnet_kethua;

import java.lang.reflect.Member;
import java.util.Scanner;

/**
 *
 * @author MYPC
 */
public class LibraryManagemnet_KeThua {

    /**
     * @param args the command line arguments
     */
   
      /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author MYPC
 */


    private Scanner sc = new Scanner(System.in);

    private Book_KeThua[] books = new Book_KeThua[1000];
    private int bookCount = 0;

    private Member[] members = new Member[1000];
    private int memberCount = 0;

    private BorrowRecord[] records = new BorrowRecord[1000];
    private int borrowCount = 0;

    public void start() {
        initData();
        while (true) {
            System.out.println("===========================================");
            System.out.println("LIBRARY MANAGEMENT SYSTEM");
            System.out.println("===========================================");
            System.out.println("1. Manage Books\n2. Manage Members\n3. Borrowing/Returning\n4. Reports\n5. Exit");
            System.out.print("Choose an option: ");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    manageBookMenu();
                    break;
                case 2:
                    manageMemberMenu();
                    break;
                case 3:
                    borrowReturnMenu();
                    break;
                case 4:
                    reportMenu();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public void manageBookMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("----------- MANAGE BOOK -----------");
            System.out.println("1. Add new book with details (ID, title, author, genre, publication year, quantity, …).\n"
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
                    addBook();
                    break;
                case 2:
                    updateBook();
                    break;
                case 3:
                    removeBook();
                    break;
                case 4:
                    viewBooks();
                    break;
                case 5:
                    searchBook();
                    break;
                case 6:
                    isRunning = false;
                    break;
            }
        }
    }

    public void addBook() {
        System.out.println("- - - - - - - - ADD BOOK - - - - - - - -");
        System.out.print("Book ID: ");
        String id = sc.nextLine();
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Author: ");
        String author = sc.nextLine();
        System.out.print("Genre: ");
        String genre = sc.nextLine();
        System.out.print("Publication Year: ");
        int year = sc.nextInt();
        System.out.print("Quantity: ");
        int quantity = sc.nextInt();

        System.out.print("[1] Save [2] Cancel\nEnter your choice: ");
        if (sc.nextInt() == 1) {

            books[bookCount] = new Book_KeThua(id, title, author, genre, year, quantity);
            bookCount++;
            System.out.println("=> Book saved successfully!");
        } else {
            System.out.println("=> Operation cancelled.");
        }
        sc.nextLine();
    }

    public void updateBook() {
        System.out.print("Enter Book ID: ");
        String id = sc.nextLine();
        int index = getBookIndex(id);

        if (index == -1) {
            System.out.println("Fail: Book not found!");
            return;
        }

        Book_KeThua b = books[index];
        System.out.println("Title: " + b.getTitle() + " | Current Qty: " + b.getQuantity());
        System.out.print("Enter new Quantity : ");
        int newQty = sc.nextInt();

        System.out.print("[1] Save [2] Cancel\nEnter your choice: ");
        if (sc.nextInt() == 1) {
            b.setQuantity(newQty);
            System.out.println("=> Book updated successfully!");
        }
        sc.nextLine();
    }

    public void removeBook() {
        System.out.print("Enter Book ID: ");
        String id = sc.nextLine();
        int index = getBookIndex(id);

        if (index == -1) {
            System.out.println("Fail: Book not found!");
            return;
        }

        for (int j = 0; j < borrowCount; j++) {
            if (records[j].getBook().getId().equalsIgnoreCase(id) && !records[j].isReturned()) {
                System.out.println("Fail: Cannot remove. This book is currently borrowed.");
                return;
            }
        }

        System.out.print("[1] Remove [2] Cancel\nEnter your choice: ");
        if (sc.nextInt() == 1) {

            for (int i = index; i < bookCount - 1; i++) {
                books[i] = books[i + 1];
            }
            books[bookCount - 1] = null;
            bookCount--;
            System.out.println("=> Book removed successfully!");
        }
        sc.nextLine();
    }

    public void viewBooks() {
        System.out.println("\n- - - - - - - - BOOK LIST - - - - - - - -");
        System.out.printf("%-5s %-25s %-20s %-15s %-5s %-3s\n", "ID", "Title", "Author", "Genre", "Year", "Qty");
        System.out.println("----------------------------------------------------------------------------------");
        for (int i = 0; i < bookCount; i++) {
            Book_KeThua b = books[i];
            System.out.printf("%-5s %-25s %-20s %-15s %-5d %-3d\n",
                    b.getId(), b.getTitle(), b.getAuthor(), b.getGenre(), b.getPublicationYear(), b.getQuantity());
        }
    }

    public void searchBook() {
        System.out.print("Enter keyword (Title, Author, or Genre): ");
        String keyword = sc.nextLine().trim().toLowerCase();
        boolean isFound = false;

        System.out.printf("%-5s %-25s %-20s %-15s %-5s %-3s\n", "ID", "Title", "Author", "Genre", "Year", "Qty");
        for (int i = 0; i < bookCount; i++) {
            Book_KeThua b = books[i];
            if (b.getTitle().toLowerCase().contains(keyword)
                    || b.getAuthor().toLowerCase().contains(keyword)
                    || b.getGenre().toLowerCase().contains(keyword)) {

                System.out.printf("%-5s %-25s %-20s %-15s %-5d %-3d\n",
                        b.getId(), b.getTitle(), b.getAuthor(), b.getGenre(), b.getPublicationYear(), b.getQuantity());
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("No books matched your search criteria.");
        }
    }

    public void manageMemberMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("----------- MANAGE MEMBER -----------");
            System.out.println("1. Add new member (ID, name, phone, email, …).\n"
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
                    addMember();
                    break;
                case 2:
                    updateMember();
                    break;
                case 3:
                    removeMember();
                    break;
                case 4:
                    viewMembers();
                    break;
                case 5:
                    searchMember();
                    break;
                case 6:
                    isRunning = false;
                    break;
            }
        }
    }

    public void addMember() {
        System.out.print("Enter Member ID: ");
        String id = sc.nextLine();
        if (getMemberIndex(id) != -1) {
            System.out.println("Fail: Member ID already exists!");
            return;
        }
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("[1] Confirm [2] Cancel\nEnter your choice: ");
        if (sc.nextInt() == 1) {
            members[memberCount] = new Member(id, name, phone, email);
            memberCount++;
            System.out.println("=> Member saved successfully!");
        }
        sc.nextLine();
    }

    public void updateMember() {
        System.out.print("Enter Member ID: ");
        String id = sc.nextLine();
        int index = getMemberIndex(id);

        if (index == -1) {
            System.out.println("Fail: Member not found!");
            return;
        }
        Member m = members[index];
        System.out.print("New Name (leave blank to skip): ");
        String name = sc.nextLine();
        System.out.print("New Phone (leave blank to skip): ");
        String phone = sc.nextLine();
        System.out.print("New Email (leave blank to skip): ");
        String email = sc.nextLine();

        System.out.print("[1] Confirm [2] Cancel\nEnter your choice: ");
        if (sc.nextInt() == 1) {
            if (!name.isEmpty()) {
                m.setName(name);
            }
            if (!phone.isEmpty()) {
                m.setPhone(phone);
            }
            if (!email.isEmpty()) {
                m.setEmail(email);
            }
            System.out.println("=> Member updated successfully!");
        }
        sc.nextLine();
    }

    public void removeMember() {
        System.out.print("Enter Member ID to remove: ");
        String id = sc.nextLine();
        int index = getMemberIndex(id);
        if (index == -1) {
            System.out.println("Fail: Member not found!");
            return;
        }

        for (int j = 0; j < borrowCount; j++) {
            if (records[j].getMember().getId().equalsIgnoreCase(id) && !records[j].isReturned()) {
                System.out.println("Fail: Cannot remove. Member has outstanding borrowed books.");
                return;
            }
        }

        System.out.print("[1] Confirm [2] Cancel\nEnter your choice: ");
        if (sc.nextInt() == 1) {
            for (int i = index; i < memberCount - 1; i++) {
                members[i] = members[i + 1];
            }
            members[memberCount - 1] = null;
            memberCount--;
            System.out.println("=> Member removed successfully!");
        }
        sc.nextLine();
    }

    public void viewMembers() {
        for (int i = 0; i < memberCount; i++) {
            Member m = members[i];
            System.out.printf("Member %d: ID: %s, Name: %s, Phone: %s, Email: %s\n",
                    (i + 1), m.getId(), m.getName(), m.getPhone(), m.getEmail());
        }
    }

    public void searchMember() {
        System.out.print("Enter Name or ID: ");
        String kw = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < memberCount; i++) {
            Member m = members[i];
            if (m.getId().equalsIgnoreCase(kw) || m.getName().equalsIgnoreCase(kw)) {
                System.out.printf("Found -> ID: %s, Name: %s, Phone: %s\n", m.getId(), m.getName(), m.getPhone());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No members found.");
        }
    }

    public void borrowReturnMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("----------- BORROW/RETURN -----------");
            System.out.println("1. Borrow a book (record book ID, member ID, borrow date).\n"
                    + "2. Return a book (record return date, calculate fine if overdue).\n"
                    + "3. View all borrowed books (currently out).\n"
                    + "4. View borrowing history for a specific member.\n"
                    + "5. Return");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    borrowBook();
                    break;
                case 2:
                    returnBook();
                    break;
                case 3:
                    viewBorrowBook();
                    break;
                case 4:
                    viewHistory();
                    break;
                case 5:
                    isRunning = false;
                    break;
            }
        }
    }

    public void borrowBook() {
        System.out.print("Member ID: ");
        String memId = sc.nextLine();
        int mIdx = getMemberIndex(memId);
        if (mIdx == -1) {
            System.out.println("Fail: Member not found!");
            return;
        }

        System.out.print("Book ID: ");
        String bookId = sc.nextLine();
        int bIdx = getBookIndex(bookId);
        if (bIdx == -1) {
            System.out.println("Fail: Book not found!");
            return;
        }

        Member m = members[mIdx];
        Book_KeThua b = books[bIdx];

        if (b.getQuantity() <= 0) {
            System.out.println("Fail: Out of stock!");
            return;
        }

        int activeBorrows = 0;
        for (int i = 0; i < borrowCount; i++) {
            if (records[i].getMember().getId().equalsIgnoreCase(memId) && !records[i].isReturned()) {
                activeBorrows++;
            }
        }
        if (activeBorrows >= 3) {
            System.out.println("Fail: Limit reached (3 books).");
            return;
        }

        System.out.print("Borrow Date (YYYY-MM-DD): ");
        String bDate = sc.nextLine();
        System.out.print("Due Date (YYYY-MM-DD): ");
        String dDate = sc.nextLine();

        System.out.print("[1] Confirm [2] Cancel\nEnter your choice: ");
        if (sc.nextInt() == 1) {
            // Tạo bản ghi lưu thẳng Đối tượng
            records[borrowCount] = new BorrowRecord(m, b, bDate, dDate);
            borrowCount++;

            b.setQuantity(b.getQuantity() - 1);
            b.setBorrowFrequency(b.getBorrowFrequency() + 1);
            m.setBorrowedCount(m.getBorrowedCount() + 1);

            System.out.println("Success: Book borrowed!");
        }
        sc.nextLine();
    }

    public void returnBook() {
        System.out.print("Member ID: ");
        String memId = sc.nextLine();
        System.out.print("Book ID: ");
        String bookId = sc.nextLine();

        int tIdx = -1;
        for (int i = 0; i < borrowCount; i++) {
            if (records[i].getMember().getId().equalsIgnoreCase(memId)
                    && records[i].getBook().getId().equalsIgnoreCase(bookId)
                    && !records[i].isReturned()) {
                tIdx = i;
                break;
            }
        }
        if (tIdx == -1) {
            System.out.println("Fail: Record not found.");
            return;
        }

        System.out.print("Overdue days (0 if none): ");
        int days = sc.nextInt();

        BorrowRecord rec = records[tIdx];
        rec.setReturned(true);
        rec.getBook().setQuantity(rec.getBook().getQuantity() + 1); // Trả lại kho 1 cuốn

        if (days > 0) {
            System.out.printf("Returned. Fine: %,d VND.\n", (long) days * 5000);
        } else {
            System.out.println("Returned successfully. No fine.");
        }
        sc.nextLine();
    }

    public void viewBorrowBook() {
        boolean hasData = false;
        for (int i = 0; i < borrowCount; i++) {
            if (!records[i].isReturned()) {
                BorrowRecord r = records[i];
                System.out.printf("Member: %s, Book: %s, Borrowed: %s\n",
                        r.getMember().getName(), r.getBook().getTitle(), r.getBorrowDate());
                hasData = true;
            }
        }
        if (!hasData) {
            System.out.println("No books are currently borrowed.");
        }
    }

    public void viewHistory() {

        boolean hasData = false;
        for (int i = 0; i < borrowCount; i++) {
            BorrowRecord r = records[i];
            System.out.printf("Member: %s, Book: %s, Borrowed: %s, Status: %s\n",
                    r.getMember().getName(), r.getBook().getTitle(), r.getBorrowDate(),
                    r.isReturned() ? "Returned" : "Borrowing");
            hasData = true;
        }
        if (!hasData) {
            System.out.println("No history found.");
        }
    }

    public void reportMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n----------- REPORT -----------");
            System.out.println("1. Generate a list of all currently borrowed books.\n"
                    + "2. Generate a list of overdue books.\n"
                    + "3. List most popular books (based on borrowing frequency).\n"
                    + "4. List members with the most borrowings.\n"
                    + "5. Return");
            System.out.print("Choose: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    viewBorrowBook();
                    break;
                case 2:
                    System.out.print("Enter Today (YYYY-MM-DD): ");
                    String today = sc.nextLine();
                    boolean overdue = false;
                    for (int i = 0; i < borrowCount; i++) {
                        if (!records[i].isReturned() && records[i].getDueDate().compareTo(today) < 0) {
                            System.out.printf("- Book: %s | Borrower: %s | Due: %s\n",
                                    records[i].getBook().getTitle(), records[i].getMember().getName(), records[i].getDueDate());
                            overdue = true;
                        }
                    }
                    if (!overdue) {
                        System.out.println("No overdue books.");
                    }
                    break;
                case 3:
                    int maxB = 0;
                    for (int i = 0; i < bookCount; i++) {
                        if (books[i].getBorrowFrequency() > maxB) {
                            maxB = books[i].getBorrowFrequency();
                        }
                    }
                    if (maxB == 0) {
                        System.out.println("No records.");
                        break;
                    }
                    for (int i = 0; i < bookCount; i++) {
                        if (books[i].getBorrowFrequency() == maxB) {
                            System.out.println("- " + books[i].getTitle() + " (" + maxB + " times)");
                        }
                    }
                    break;
                case 4:
                    int maxM = 0;
                    for (int i = 0; i < memberCount; i++) {
                        if (members[i].getBorrowedCount() > maxM) {
                            maxM = members[i].getBorrowedCount();
                        }
                    }
                    if (maxM == 0) {
                        System.out.println("No records.");
                        break;
                    }
                    for (int i = 0; i < memberCount; i++) {
                        if (members[i].getBorrowedCount() == maxM) {
                            System.out.println("- " + members[i].getName() + " (" + maxM + " books)");
                        }
                    }
                    break;
                case 5:
                    isRunning = false;
                    break;
            }
        }
    }

    public int getBookIndex(String id) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public int getMemberIndex(String id) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i].getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public void initData() {
        books[0] = new Book_KeThua("B001", "The Great Gatsby", "F. Scott Fitzgerald", "Classic", 1925, 7);
        books[1] = new Book_KeThua("B002", "1984", "George Orwell", "Dystopian", 1949, 3);
        bookCount = 2;

        members[0] = new Member("A001", "Lê Đỗ Anh Khoa", "0901234567", "khoa.le@gmail.com") ;
        members[1] = new Member("A002", "Nguyen Van B", "0987654321", "abc@gmail.com");
        memberCount = 2;
    }

    public static void main(String[] args) {
        LibraryManagemnet_KeThua app = new LibraryManagemnet_KeThua();
        app.start();
    }



    
    
}
