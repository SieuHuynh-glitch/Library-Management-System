/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_project;


/**
 *
 * @author MYPC
 */
public class BorrowRecord {
    
    private Member member;
    private Book book;
    private String borrowDate;
    private String dueDate;
    private boolean isReturned;

    public BorrowRecord(Member member, Book book, String borrowDate, String dueDate) {
        this.member = member;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.isReturned = false; 
    }

    public Member getMember() { return this.member; }
    public void setMember(Member member) { this.member = member; }

    public Book getBook() { return this.book; }
    public void setBook(Book book) { this.book = book; }

    public String getBorrowDate() { return this.borrowDate; }
    public void setBorrowDate(String borrowDate) { this.borrowDate = borrowDate; }

    public String getDueDate() { return this.dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public boolean isReturned() { return this.isReturned; }
    public void setReturned(boolean isReturned) { this.isReturned = isReturned; }

}
