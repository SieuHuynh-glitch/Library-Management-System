/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.ke.thua;

/**
 *
 * @author Admin
 */
public class Memberkethua extends Person {

    private int borrowedCount;

    public Memberkethua(String id, String name, String phone, String email) {
        super(id, name, phone, email);
        this.borrowedCount = 0;
    }

    public int getBorrowedCount() {
        return this.borrowedCount;
    }

    public void setBorrowedCount(int borrowedCount) {
        this.borrowedCount = borrowedCount;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Borrowed Count: " + this.borrowedCount;
    }
}
