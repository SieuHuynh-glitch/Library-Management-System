/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagemnet_kethua;

/**
 *
 * @author MYPC
 */
public class Document {
  
    private String id;
    String title;
    private int publicationYear;
    private int quantity;
    private int borrowFrequency;

    public Document(String id, String title, int publicationYear, int quantity) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.quantity = quantity;
        this.borrowFrequency = 0;
    }


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getPublicationYear() { return publicationYear; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getBorrowFrequency() { return borrowFrequency; }
    public void setBorrowFrequency(int borrowFrequency) { this.borrowFrequency = borrowFrequency; }

    public void displayDetails() {
        System.out.printf("%-5s %-30s %-10d %-5d", id, title, publicationYear, quantity);
    }

}

