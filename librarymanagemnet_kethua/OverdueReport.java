/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagemnet_kethua;

/**
 *
 * @author MYPC
 */
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

        for (int i = 0; i < getBorrowCount(); i++) {

            if (!getRecords()[i].isReturned()
                    && getRecords()[i].getDueDate().compareTo(today) < 0) {

                System.out.println(
                        getRecords()[i].getBook().getTitle()
                        + " - "
                        + getRecords()[i].getMember().getName());

                found = true;
            }
        }

        if (!found) {
            System.out.println("No overdue books found.");
        }
    }
}
