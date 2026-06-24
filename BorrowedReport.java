package oop_project;

public class BorrowedReport extends Report {

    public BorrowedReport(Book[] books, int bookCount,
            Member[] members, int memberCount,
            BorrowRecord[] records, int borrowCount) {

        super(books, bookCount, members, memberCount,
                records, borrowCount);
    }

    public void showReport() {

        System.out.println("\n--- CURRENTLY BORROWED BOOKS ---");

        boolean found = false;

        for (int i = 0; i < borrowCount; i++) {

            if (!records[i].isReturned()) {

                System.out.println(
                        records[i].getBook().getTitle()
                        + " - Borrowed by "
                        + records[i].getMember().getName());

                found = true;
            }
        }

        if (!found) {
            System.out.println("No books are currently borrowed.");
        }
    }
}
