package oop_project;

// =================================================================
// 1. LỚP CHA TRƯỜNG TƯỢNG
// =================================================================
public abstract class Member {
    protected String id;
    protected String name;
    protected String phone;
    protected String email;
    protected int maxBorrowLimit; // Thuộc tính protected (# maxBorrowLimit: int)

    // Hàm khởi tạo của lớp cha
    public Member(String id, String name, String phone, String email, int maxBorrowLimit) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.maxBorrowLimit = maxBorrowLimit;
    }

    // Các hàm lấy dữ liệu công khai (+ getter)
    public String getId() { 
        return this.id; 
    }
    
    public String getName() { 
        return this.name; 
    }
    
    public int getMaxBorrowLimit() { 
        return this.maxBorrowLimit; 
    }

    // Phương thức trừu tượng tính tiền phạt (+ calculateFine(daysOverdue: int) : long)
    public abstract long calculateFine(int daysOverdue); 
}

// =================================================================
// 2. LỚP CON THÀNH VIÊN THƯỜNG 
// =================================================================
class RegularMember extends Member {
    
    public RegularMember(String id, String name, String phone, String email) {
        // Gọi super(...) gửi thông tin lên cha, số 3 chính là maxBorrowLimit của khách thường
        super(id, name, phone, email, 3); 
    }

    @Override
    public long calculateFine(int daysOverdue) {
        return (long) daysOverdue * 5000; // Phạt thành viên thường 5,000 VND / ngày quá hạn
    }
}

// =================================================================
// 3. LỚP CON THÀNH VIÊN VIP 
// =================================================================
class PremiumMember extends Member {
    
    public PremiumMember(String id, String name, String phone, String email) {
        // Gọi super(...) gửi thông tin lên cha, số 5 là maxBorrowLimit ưu đãi của khách VIP
        super(id, name, phone, email, 5); 
    }

    @Override
    public long calculateFine(int daysOverdue) {
        return (long) daysOverdue * 2000; // Ưu đãi VIP: Phạt rẻ hơn, chỉ 2,000 VND / ngày quá hạn
    }
}
