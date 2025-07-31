import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private boolean available;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Member {
    private int id;
    private String name;

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class Library {
    private Map<Integer, Book> books;
    private List<Member> members;

    public Library() {
        books = new HashMap<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void displayBooks() {
        System.out.println("Books available in the library:");
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                System.out.println("ID: " + book.getId() + ", Title: " + book.getTitle());
            }
        }
    }

    public void borrowBook(int memberId, int bookId) {
        Member member = findMember(memberId);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        Book book = books.get(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is already borrowed.");
            return;
        }

        book.setAvailable(false);
        System.out.println("Book \"" + book.getTitle() + "\" borrowed by " + member.getName() + ".");
    }

    public void returnBook(int bookId) {
        Book book = books.get(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (book.isAvailable()) {
            System.out.println("Book is already available in the library.");
            return;
        }

        book.setAvailable(true);
        System.out.println("Book \"" + book.getTitle() + "\" returned to the library.");
    }

    private Member findMember(int memberId) {
        for (Member member : members) {
            if (member.getId() == memberId) {
                return member;
            }
        }
        return null;
    }
}

class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        // Adding some books and members for demonstration
        library.addBook(new Book(101, "Java Programming"));
        library.addBook(new Book(102, "Data Structures and Algorithms"));
        library.addBook(new Book(103, "Database Management"));

        library.addMember(new Member(1001, "John Doe"));
        library.addMember(new Member(1002, "Jane Smith"));

        boolean exit = false;

        while (!exit) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Display available books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter your member ID: ");
                    int memberId = scanner.nextInt();
                    System.out.print("Enter the ID of the book you want to borrow: ");
                    int bookIdToBorrow = scanner.nextInt();
                    library.borrowBook(memberId, bookIdToBorrow);
                    break;
                case 3:
                    System.out.print("Enter the ID of the book you want to return: ");
                    int bookIdToReturn = scanner.nextInt();
                    library.returnBook(bookIdToReturn);
                    break;
                case 4:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
