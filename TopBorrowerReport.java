package oop_project;

public class TopBorrowerReport extends Report {

    public TopBorrowerReport(Book[] books, int bookCount,
            Member[] members, int memberCount,
            BorrowRecord[] records, int borrowCount) {

        super(books, bookCount, members, memberCount,
                records, borrowCount);
    }

    public void showReport() {

        System.out.println("\n--- TOP BORROWERS ---");

        int max = 0;

        for (int i = 0; i < memberCount; i++) {

            if (members[i].getBorrowedCount() > max) {
                max = members[i].getBorrowedCount();
            }
        }

        for (int i = 0; i < memberCount; i++) {

            if (members[i].getBorrowedCount() == max && max > 0) {

                System.out.println(
                        members[i].getName()
                        + " ("
                        + max
                        + " borrowings)");
            }
        }
    }
}
