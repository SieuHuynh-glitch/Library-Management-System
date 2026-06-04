# Library-Management-System

I Context
A local community library aims to modernize its operations by implementing a simple
computer-based system. This system will manage book information, library members,
and the borrowing/returning of books. The primary goal is to improve efficiency in
managing the library’s collection and member activities, ensuring accurate record
keeping and easy access to information.
The system should store detailed information such as book ID, title, author, genre,
publication year, and current status (available/borrowed). For members, it needs to
track member ID, name, contact information, and a list of currently borrowed books.
The system must also manage borrowing transactions, including the book borrowed,
member, borrow date, and return date.
Library staff will use the system to register new books and members, process
borrowing and returning of books, and generate various reports. Key business rules,
such as unique identification for books and members, checking book availability
before borrowing, and calculating overdue fines, must be enforced.
Overall, the system seeks to enhance the library’s service quality, streamline daily
tasks, and provide better insights into library usage

II Functional Requirements

Book Management (Lê Đỗ Anh Khoa)

1. Add new book with details (ID, title, author, genre, publication year, quantity, …).
2. Update book information.
3. Remove a book (only if not currently borrowed).
4. View all books.
5. Search books by title, author, or genre.

Member Management (Huỳnh Hoàng Siêu)

1. Add new member (ID, name, phone, email, …).
2. Update member information.
3. Remove a member (only if no outstanding borrowed books).
4. View all members.
5. Search members by name or ID.

Borrowing Management (Nguyễn Tuấn Kiệt)

1. Borrow a book (record book ID, member ID, borrow date).
2. Return a book (record return date, calculate fine if overdue).
3. View all borrowed books (currently out).
4. View borrowing history for a specific member.

Reporting (Ngô Phạm Nguyệt Minh)

1. Generate a list of all currently borrowed books.
2. Generate a list of overdue books.
3. List most popular books (based on borrowing frequency).
4. List members with the most borrowings

III Business Rules
BR1: Each Book ID and Member ID must be unique and cannot be modified.
BR2: Book title, author, and genre must not be empty.
BR3: A member must exist before borrowing a book.
BR4: A book must be available (stock > 0) before it can be borrowed.
BR5: A book can only be borrowed if the member has not exceeded their
borrowing limit (e.g., 3 books at a time).
BR6: Borrow date must be current or in the past; return date must be after
borrow date.
BR7: Overdue fine is calculated based on the number of days past the due date
(e.g., 5,000 VND per day).
BR8: Book stock is reduced upon borrowing and increased upon returning.
BR9: All inputs must be validated before processing.
BR10: Popular books are determined by the total number of times they have
been borrowed
