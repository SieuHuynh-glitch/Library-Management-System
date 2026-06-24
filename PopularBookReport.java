package oop_project;

public class PopularBookReport extends Report {

    public PopularBookReport(Book[] books, int bookCount,
            Member[] members, int memberCount,
            BorrowRecord[] records, int borrowCount) {

        super(books, bookCount, members, memberCount,
                records, borrowCount);
    }

    public void showReport() {

        System.out.println("\n--- MOST POPULAR BOOKS ---");

        int max = 0;

        for (int i = 0; i < bookCount; i++) {

            if (books[i].getBorrowFrequency() > max) {
                max = books[i].getBorrowFrequency();
            }
        }

        for (int i = 0; i < bookCount; i++) {

            if (books[i].getBorrowFrequency() == max && max > 0) {

                System.out.println(
                        books[i].getTitle()
                        + " (Borrowed "
                        + max
                        + " times)");
            }
        }
    }
}
