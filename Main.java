package project.ke.thua;

import java.util.Scanner;

public class Main {

    private Scanner sc = new Scanner(System.in);

    private Member[] members = new Member[1000];
    private int memberCount = 0;

    public void start() {
        this.initData();
        this.manageMemberMenu();
    }

    public void manageMemberMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("===========================================");
            System.out.println("         MEMBER MANAGEMENT SYSTEM          ");
            System.out.println("===========================================");
            System.out.println("1. Add new member.\n"
                    + "2. Update member information.\n"
                    + "3. Remove a member.\n"
                    + "4. View all members.\n"
                    + "5. Search members by name or ID.\n"
                    + "6. Exit");
            System.out.print("Choose an option: ");
            int option = this.sc.nextInt();
            this.sc.nextLine();
            switch (option) {
                case 1:
                    this.addMember();
                    break;
                case 2:
                    this.updateMember();
                    break;
                case 3:
                    this.removeMember();
                    break;
                case 4:
                    this.viewMembers();
                    break;
                case 5:
                    this.searchMember();
                    break;
                case 6:
                    isRunning = false;
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public void addMember() {
        System.out.print("Enter Member ID: ");
        String id = this.sc.nextLine();
        if (this.getMemberIndex(id) != -1) {
            System.out.println("Fail: Member ID already exists!");
            return;
        }
        System.out.print("Enter Name: ");
        String name = this.sc.nextLine();
        System.out.print("Enter Phone: ");
        String phone = this.sc.nextLine();
        System.out.print("Enter Email: ");
        String email = this.sc.nextLine();

        System.out.print("[1] Confirm [2] Cancel\nEnter your choice: ");
        if (this.sc.nextInt() == 1) {
            this.members[this.memberCount] = new Member(id, name, phone, email);
            this.memberCount++;
            System.out.println("=> Member saved successfully!");
        }
        this.sc.nextLine();
    }

    public void updateMember() {
        System.out.print("Enter Member ID: ");
        String id = this.sc.nextLine();
        int index = this.getMemberIndex(id);

        if (index == -1) {
            System.out.println("Fail: Member not found!");
            return;
        }
        Member m = this.members[index];
        System.out.print("New Name (leave blank to skip): ");
        String name = this.sc.nextLine();
        System.out.print("New Phone (leave blank to skip): ");
        String phone = this.sc.nextLine();
        System.out.print("New Email (leave blank to skip): ");
        String email = this.sc.nextLine();

        System.out.print("[1] Confirm [2] Cancel\nEnter your choice: ");
        if (this.sc.nextInt() == 1) {
            if (!name.isEmpty()) {
                m.setName(name);
            }
            if (!phone.isEmpty()) {
                m.setPhone(phone);
            }
            if (!email.isEmpty()) {
                m.setEmail(email);
            }
            System.out.println("=> Member updated successfully!");
        }
        this.sc.nextLine();
    }

    public void removeMember() {
        System.out.print("Enter Member ID to remove: ");
        String id = this.sc.nextLine();
        int index = this.getMemberIndex(id);
        if (index == -1) {
            System.out.println("Fail: Member not found!");
            return;
        }

        System.out.print("[1] Confirm [2] Cancel\nEnter your choice: ");
        if (this.sc.nextInt() == 1) {
            for (int i = index; i < this.memberCount - 1; i++) {
                this.members[i] = this.members[i + 1];
            }
            this.members[this.memberCount - 1] = null;
            this.memberCount--;
            System.out.println("=> Member removed successfully!");
        }
        this.sc.nextLine();
    }

    public void viewMembers() {
        for (int i = 0; i < this.memberCount; i++) {
            Member m = this.members[i];
            System.out.printf("Member %d: ID: %s, Name: %s, Phone: %s, Email: %s\n",
                    (i + 1), m.getId(), m.getName(), m.getPhone(), m.getEmail());
        }
    }

    public void searchMember() {
        System.out.print("Enter Name or ID: ");
        String kw = this.sc.nextLine();
        boolean found = false;
        for (int i = 0; i < this.memberCount; i++) {
            Member m = this.members[i];
            if (m.getId().equalsIgnoreCase(kw) || m.getName().equalsIgnoreCase(kw)) {
                System.out.printf("Found -> ID: %s, Name: %s, Phone: %s\n", m.getId(), m.getName(), m.getPhone());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No members found.");
        }
    }

    public int getMemberIndex(String id) {
        for (int i = 0; i < this.memberCount; i++) {
            if (this.members[i].getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public void initData() {
        this.members[0] = new Member("A001", "Lê Đỗ Anh Khoa", "0901234567", "khoa.le@gmail.com");
        this.members[1] = new Member("A002", "Nguyen Van B", "0987654321", "abc@gmail.com");
        this.memberCount = 2;
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
}