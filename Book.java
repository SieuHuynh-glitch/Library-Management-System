/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_project;

/**
 *
 * @author MYPC
 */
public class Book {

    private String id;
    private String title;
    private String author;
    private String genre;
    private int publicationYear;
    private int quantity;
    private int borrowFrequency; 

    public Book(String id, String title, String author, String genre, int publicationYear, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.quantity = quantity;
        this.borrowFrequency = 0;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublicationYear() {
        return this.publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBorrowFrequency() {
        return this.borrowFrequency;
    }

    public void setBorrowFrequency(int borrowFrequency) {
        this.borrowFrequency = borrowFrequency;
    }
}
