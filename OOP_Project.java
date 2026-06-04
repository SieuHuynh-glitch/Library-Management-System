package oop_project;

import java.util.Scanner;

public class OOP_Project {

    private Scanner sc = new Scanner(System.in);

    private Book[] books = new Book[1000];
    private int bookCount = 0;

    private Member[] members = new Member[1000];
    private int memberCount = 0;

    private BorrowRecord[] records = new BorrowRecord[1000];
    private int borrowCount = 0;

    private Report reportObj = new Report();

    public void start() {
        this.initData(); 
        while (true) {
            System.out.println("===========================================");
            System.out.println("LIBRARY MANAGEMENT SYSTEM");
            System.out.println("===========================================");
            System.out.println("1. Manage Books\n2. Manage Members\n3. Borrowing/Returning\n4. Reports\n5. Exit");
            System.out.print("Choose an option: ");

            int option = this.sc.nextInt();
            this.sc.nextLine();
            switch (option) {
                case 1:
                    this.manageBookMenu();
                    break;
                case 2:
                    this.manageMemberMenu();
                    break;
                case 3:
                    this.borrowReturnMenu();
                    break;
                case 4:
                    this.reportObj.setData(this.books, this.bookCount, this.members, this.memberCount, this.records, this.borrowCount);
                    this.reportObj.showMenu();
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
            int option = this.sc.nextInt();
            this.sc.nextLine();
            switch (option) {
                case 1:
                    this.addBook();
                    break;
                case 2:
                    this.updateBook();
                    break;
                case 3:
                    this.removeBook();
                    break;
                case 4:
                    this.viewBooks();
                    break;
                case 5:
                    this.searchBook();
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
        String id = this.sc.nextLine();
        System.out.print("Title: ");
        String title = this.sc.nextLine();
        System.out.print("Author: ");
        String author = this.sc.nextLine();
        System.out.print("Genre: ");
        String genre = this.sc.nextLine();
        System.out.print("Publication Year: ");
        int year = this.sc.nextInt();
        System.out.print("Quantity: ");
        int quantity = this.sc.nextInt();

        System.out.print("[1] Save [2] Cancel\nEnter your choice: ");
        if (this.sc.nextInt() == 1) {
            this.books[this.bookCount] = new Book(id, title, author, genre, year, quantity);
            this.bookCount++;
            System.out.println("=> Book saved successfully!");
        } else {
            System.out.println("=> Operation cancelled.");
        }
        this.sc.nextLine();
    }

    public void updateBook() {
        System.out.print("Enter Book ID: ");
        String id = this.sc.nextLine();
        int index = this.getBookIndex(id);

        if (index == -1) {
            System.out.println("Fail: Book not found!");
            return;
        }

        Book b = this.books[index];
        System.out.println("Title: " + b.getTitle() + " | Current Qty: " + b.getQuantity());
        System.out.print("Enter new Quantity : ");
        int newQty = this.sc.nextInt();

        System.out.print("[1] Save [2] Cancel\nEnter your choice: ");
        if (this.sc.nextInt() == 1) {
            b.setQuantity(newQty);
            System.out.println("=> Book updated successfully!");
        }
        this.sc.nextLine();
    }

    public void removeBook() {
        System.out.print("Enter Book ID: ");
        String id = this.sc.nextLine();
        int index = this.getBookIndex(id);

        if (index == -1) {
            System.out.println("Fail: Book not found!");
            return;
        }

        for (int j = 0; j < this.borrowCount; j++) {
            if (this.records[j].getBook().getId().equalsIgnoreCase(id) && !this.records[j].isReturned()) {
                System.out.println("Fail: Cannot remove. This book is currently borrowed.");
                return;
            }
        }

        System.out.print("[1] Remove [2] Cancel\nEnter your choice: ");
        if (this.sc.nextInt() == 1) {
            for (int i = index; i < this.bookCount - 1; i++) {
                this.books[i] = this.books[i + 1];
            }
            this.books[this.bookCount - 1] = null;
            this.bookCount--;
            System.out.println("=> Book removed successfully!");
        }
        this.sc.nextLine();
    }

    public void viewBooks() {
        System.out.println("\n- - - - - - - - BOOK LIST - - - - - - - -");
        System.out.printf("%-5s %-25s %-20s %-15s %-5s %-3s\n", "ID", "Title", "Author", "Genre", "Year", "Qty");
        System.out.println("----------------------------------------------------------------------------------");
        for (int i = 0; i < this.bookCount; i++) {
            Book b = this.books[i];
            System.out.printf("%-5s %-25s %-20s %-15s %-5d %-3d\n",
                    b.getId(), b.getTitle(), b.getAuthor(), b.getGenre(), b.getPublicationYear(), b.getQuantity());
        }
    }

    public void searchBook() {
        System.out.print("Enter keyword (Title, Author, or Genre): ");
        String keyword = this.sc.nextLine().trim().toLowerCase();
        boolean isFound = false;

        System.out.printf("%-5s %-25s %-20s %-15s %-5s %-3s\n", "ID", "Title", "Author", "Genre", "Year", "Qty");
        for (int i = 0; i < this.bookCount; i++) {
            Book b = this.books[i];
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
            int option = this.sc.nextInt();
            this.sc.nextLine();
            switch (option) {
                case 1:
                    this.addMember();
                    break;
                case 2:
                    this.updateMember();
                    break;
                case 3:
                    this.removeMember();
                    break;
                case 4:
                    this.viewMembers();
                    break;
                case 5:
                    this.searchMember();
                    break;
                case 6:
                    isRunning = false;
                    break;
            }
        }
    }

    public void addMember() {
        System.out.print("Enter Member ID: ");
        String id = this.sc.nextLine();
        if (this.getMemberIndex(id) != -1) {
            System.out.println("Fail: Member ID already exists!");
            return;
        }
        System.out.print("Enter Name: ");
        String name = this.sc.nextLine();
        System.out.print("Enter Phone: ");
        String phone = this.sc.nextLine();
        System.out.print("Enter Email: ");
        String email = this.sc.nextLine();

        System.out.print("[1] Confirm [2] Cancel\nEnter your choice: ");
        if (this.sc.nextInt() == 1) {
            this.members[this.memberCount] = new Member(id, name, phone, email);
            this.memberCount++;
            System.out.println("=> Member saved successfully!");
        }
        this.sc.nextLine();
    }

    public void updateMember() {
        System.out.print("Enter Member ID: ");
        String id = this.sc.nextLine();
        int index = this.getMemberIndex(id);

        if (index == -1) {
            System.out.println("Fail: Member not found!");
            return;
        }
        Member m = this.members[index];
        System.out.print("New Name (leave blank to skip): ");
        String name = this.sc.nextLine();
        System.out.print("New Phone (leave blank to skip): ");
        String phone = this.sc.nextLine();
        System.out.print("New Email (leave blank to skip): ");
        String email = this.sc.nextLine();

        System.out.print("[1] Confirm [2] Cancel\nEnter your choice: ");
        if (this.sc.nextInt() == 1) {
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
        this.sc.nextLine();
    }

    public void removeMember() {
        System.out.print("Enter Member ID to remove: ");
        String id = this.sc.nextLine();
        int index = this.getMemberIndex(id);
        if (index == -1) {
            System.out.println("Fail: Member not found!");
            return;
        }

        for (int j = 0; j < this.borrowCount; j++) {
            if (this.records[j].getMember().getId().equalsIgnoreCase(id) && !this.records[j].isReturned()) {
                System.out.println("Fail: Cannot remove. Member has outstanding borrowed books.");
                return;
            }
        }

        System.out.print("[1] Confirm [2] Cancel\nEnter your choice: ");
        if (this.sc.nextInt() == 1) {
            for (int i = index; i < this.memberCount - 1; i++) {
                this.members[i] = this.members[i + 1];
            }
            this.members[this.memberCount - 1] = null;
            this.memberCount--;
            System.out.println("=> Member removed successfully!");
        }
        this.sc.nextLine();
    }

    public void viewMembers() {
        for (int i = 0; i < this.memberCount; i++) {
            Member m = this.members[i];
            System.out.printf("Member %d: ID: %s, Name: %s, Phone: %s, Email: %s\n",
                    (i + 1), m.getId(), m.getName(), m.getPhone(), m.getEmail());
        }
    }

    public void searchMember() {
        System.out.print("Enter Name or ID: ");
        String kw = this.sc.nextLine();
        boolean found = false;
        for (int i = 0; i < this.memberCount; i++) {
            Member m = this.members[i];
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
            int option = this.sc.nextInt();
            this.sc.nextLine();
            switch (option) {
                case 1:
                    this.borrowBook();
                    break;
                case 2:
                    this.returnBook();
                    break;
                case 3:
                    this.viewBorrowBook();
                    break;
                case 4:
                    this.viewHistory();
                    break;
                case 5:
                    isRunning = false;
                    break;
            }
        }
    }

    public void borrowBook() {
        System.out.print("Member ID: ");
        String memId = this.sc.nextLine();
        int mIdx = this.getMemberIndex(memId);
        if (mIdx == -1) {
            System.out.println("Fail: Member not found!");
            return;
        }

        System.out.print("Book ID: ");
        String bookId = this.sc.nextLine();
        int bIdx = this.getBookIndex(bookId);
        if (bIdx == -1) {
            System.out.println("Fail: Book not found!");
            return;
        }

        Member m = this.members[mIdx];
        Book b = this.books[bIdx];

        if (b.getQuantity() <= 0) {
            System.out.println("Fail: Out of stock!");
            return;
        }

        int activeBorrows = 0;
        for (int i = 0; i < this.borrowCount; i++) {
            if (this.records[i].getMember().getId().equalsIgnoreCase(memId) && !this.records[i].isReturned()) {
                activeBorrows++;
            }
        }
        if (activeBorrows >= 3) {
            System.out.println("Fail: Limit reached (3 books).");
            return;
        }

        System.out.print("Borrow Date (YYYY-MM-DD): ");
        String bDate = this.sc.nextLine();
        System.out.print("Due Date (YYYY-MM-DD): ");
        String dDate = this.sc.nextLine();

        System.out.print("[1] Confirm [2] Cancel\nEnter your choice: ");
        if (this.sc.nextInt() == 1) {
            this.records[this.borrowCount] = new BorrowRecord(m, b, bDate, dDate);
            this.borrowCount++;

            b.setQuantity(b.getQuantity() - 1);
            b.setBorrowFrequency(b.getBorrowFrequency() + 1);
            m.setBorrowedCount(m.getBorrowedCount() + 1);

            System.out.println("Success: Book borrowed!");
        }
        this.sc.nextLine();
    }

    public void returnBook() {
        System.out.print("Member ID: ");
        String memId = this.sc.nextLine();
        System.out.print("Book ID: ");
        String bookId = this.sc.nextLine();

        int tIdx = -1;
        for (int i = 0; i < this.borrowCount; i++) {
            if (this.records[i].getMember().getId().equalsIgnoreCase(memId)
                    && this.records[i].getBook().getId().equalsIgnoreCase(bookId)
                    && !this.records[i].isReturned()) {
                tIdx = i;
                break;
            }
        }
        if (tIdx == -1) {
            System.out.println("Fail: Record not found.");
            return;
        }

        System.out.print("Overdue days (0 if none): ");
        int days = this.sc.nextInt();

        BorrowRecord rec = this.records[tIdx];
        rec.setReturned(true);
        rec.getBook().setQuantity(rec.getBook().getQuantity() + 1); 

        if (days > 0) {
            System.out.printf("Returned. Fine: %,d VND.\n", (long) days * 5000);
        } else {
            System.out.println("Returned successfully. No fine.");
        }
        this.sc.nextLine();
    }

    public void viewBorrowBook() {
        boolean hasData = false;
        for (int i = 0; i < this.borrowCount; i++) {
            if (!this.records[i].isReturned()) {
                BorrowRecord r = this.records[i];
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
        for (int i = 0; i < this.borrowCount; i++) {
            BorrowRecord r = this.records[i];
            System.out.printf("Member: %s, Book: %s, Borrowed: %s, Status: %s\n",
                    r.getMember().getName(), r.getBook().getTitle(), r.getBorrowDate(),
                    r.isReturned() ? "Returned" : "Borrowing");
            hasData = true;
        }
        if (!hasData) {
            System.out.println("No history found.");
        }
    }

    public int getBookIndex(String id) {
        for (int i = 0; i < this.bookCount; i++) {
            if (this.books[i].getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public int getMemberIndex(String id) {
        for (int i = 0; i < this.memberCount; i++) {
            if (this.members[i].getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public void initData() {
        this.books[0] = new Book("B001", "The Great Gatsby", "F. Scott Fitzgerald", "Classic", 1925, 7);
        this.books[1] = new Book("B002", "1984", "George Orwell", "Dystopian", 1949, 3);
        this.bookCount = 2;

        this.members[0] = new Member("A001", "Lê Đỗ Anh Khoa", "0901234567", "khoa.le@gmail.com");
        this.members[1] = new Member("A002", "Nguyen Van B", "0987654321", "abc@gmail.com");
        this.memberCount = 2;
    }

    public static void main(String[] args) {
        OOP_Project app = new OOP_Project();
        app.start();
    }
}