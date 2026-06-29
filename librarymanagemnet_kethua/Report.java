/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagemnet_kethua;

/**
 *
 * @author MYPC
 */
public class Report {
    private Book[] books;
    private int bookCount;
    private Member[] members;
    private int memberCount;
    private BorrowRecord[] records;
    private int borrowCount;

    public Report(Book[] books, int bookCount,
            Member[] members, int memberCount,
            BorrowRecord[] records, int borrowCount) {

        this.books = books;
        this.bookCount = bookCount;
        this.members = members;
        this.memberCount = memberCount;
        this.records = records;
        this.borrowCount = borrowCount;
    }

    public Book[] getBooks() {
        return books;
    }

    public int getBookCount() {
        return bookCount;
    }

    public Member[] getMembers() {
        return members;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public BorrowRecord[] getRecords() {
        return records;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public void setMembers(Member[] members) {
        this.members = members;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public void setRecords(BorrowRecord[] records) {
        this.records = records;
    }

    public void setBorrowCount(int borrowCount) {
        this.borrowCount = borrowCount;
    }
    
}
