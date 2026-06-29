/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagemnet_kethua;

/**
 *
 * @author MYPC
 */
public class BorrowedReport extends Report{
    public BorrowedReport(Book[] books, int bookCount,
            Member[] members, int memberCount,
            BorrowRecord[] records, int borrowCount) {

        super(books, bookCount, members, memberCount,
                records, borrowCount);
    }

    public void showReport() {

        System.out.println("\n--- CURRENTLY BORROWED BOOKS ---");

        boolean found = false;

        for (int i = 0; i < getBorrowCount(); i++) {

            if (!getRecords()[i].isReturned()) {

                System.out.println(
                        getRecords()[i].getBook().getTitle()
                        + " - Borrowed by "
                        + getRecords()[i].getMember().getName());

                found = true;
            }
        }

        if (!found) {
            System.out.println("No books are currently borrowed.");
        }
    }
} 

