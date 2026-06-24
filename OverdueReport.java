package oop_project;

public class OverdueReport extends Report {

    public OverdueReport(Book[] books, int bookCount,
            Member[] members, int memberCount,
            BorrowRecord[] records, int borrowCount) {

        super(books, bookCount, members, memberCount,
                records, borrowCount);
    }

    public void showReport(String today) {

        System.out.println("\n--- OVERDUE BOOKS ---");

        boolean found = false;

        for (int i = 0; i < borrowCount; i++) {

            if (!records[i].isReturned()
                    && records[i].getDueDate().compareTo(today) < 0) {

                System.out.println(
                        records[i].getBook().getTitle()
                        + " - "
                        + records[i].getMember().getName());

                found = true;
            }
        }

        if (!found) {
            System.out.println("No overdue books found.");
        }
    }
}
