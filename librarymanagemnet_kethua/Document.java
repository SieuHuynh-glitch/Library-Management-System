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
    private String title;
    private int publicationYear;
    private int quantity;
    private int borrowFrequency = 0;

    public Document(String id, String title, int publicationYear, int quantity) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.quantity = quantity;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getPublicationYear() {
        return this.publicationYear;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getBorrowFrequency() {
        return this.borrowFrequency;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setBorrowFrequency(int borrowFrequency) {
        this.borrowFrequency = borrowFrequency;
    }

    public void displayDetails() {
        System.out.printf("%-5s %-30s %-10d %-5d", this.id, this.title, this.publicationYear, this.quantity);
    }
}
