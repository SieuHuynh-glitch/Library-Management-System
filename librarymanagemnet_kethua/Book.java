/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagemnet_kethua;

/**
 *
 * @author MYPC
 */
public class Book extends Document {

    private String author;
    private String genre;

    public Book(String id, String title, String author, String genre, int publicationYear, int quantity) {
        super(id, title, publicationYear, quantity);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getGenre() {
        return this.genre;
    }

    @Override
    public void displayDetails() {
        System.out.printf("%-5s %-25s %-20s %-15s %-5d %-3d\n",
                this.getId(), this.getTitle(), this.author, this.genre, this.getPublicationYear(), this.getQuantity());
    }
}
