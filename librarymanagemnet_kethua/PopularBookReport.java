package librarymanagemnet_kethua;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MYPC
 */
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

        for (int i = 0; i < getBookCount(); i++) {

            if (getBooks()[i].getBorrowFrequency() > max) {
                max = getBooks()[i].getBorrowFrequency();
            }
        }

        for (int i = 0; i < getBookCount(); i++) {

            if (getBooks()[i].getBorrowFrequency() == max && max > 0) {

                System.out.println(
                        getBooks()[i].getTitle()
                        + " (Borrowed "
                        + max
                        + " times)");
            }
        }
    }
}