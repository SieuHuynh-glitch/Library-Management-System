package oop_project;

public class Report {

    protected Book[] books;
    protected int bookCount;
    protected Member[] members;
    protected int memberCount;
    protected BorrowRecord[] records;
    protected int borrowCount;

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
}
