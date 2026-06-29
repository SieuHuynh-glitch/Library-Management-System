/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagemnet_kethua;

/**
 *
 * @author MYPC
 */
public class Member extends Person {

    private int borrowedCount;

    public Member(String id, String name, int phone, String email) {
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
