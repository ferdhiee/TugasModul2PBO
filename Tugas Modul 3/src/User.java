import java.util.ArrayList;
import java.util.Scanner;

class User {
    public void displayBooks(ArrayList<Book> books) {
        System.out.println("List of Books:");
        for (Book book : books) {
            System.out.println("Book ID: " + book.getBookId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Stock: " + book.getStock());
            System.out.println("------------------------");
        }
    }
}

class Admin extends User {
    private ArrayList<Student> studentList;

    public Admin() {
        this.studentList = new ArrayList<>();
    }

    public void logout() {
        // Admin logout logic
        System.out.println("Admin logged out.");
    }

    public void addStudent(String name, String nim, String faculty, String programStudy) {
        if (nim.length() != 15) {
            System.out.println("Invalid NIM. Must be 15 characters long.");
            return;
        }
        Student newStudent = new Student(name, nim, faculty, programStudy);
        studentList.add(newStudent);
        System.out.println("Student added successfully.");
    }

    public void inputBook(ArrayList<Book> books) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        System.out.print("Enter book stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter book type (History, Story, Text): ");
        String bookType = scanner.nextLine().toLowerCase();

        switch (bookType) {
            case "history":
                HistoryBook historyBook = new HistoryBook(title, author, stock);
                books.add(historyBook);
                break;
            case "story":
                StoryBook storyBook = new StoryBook(title, author, stock);
                books.add(storyBook);
                break;
            case "text":
                TextBook textBook = new TextBook(title, author, stock);
                books.add(textBook);
                break;
            default:
                System.out.println("Invalid book type.");
        }

        System.out.println("Book added successfully.");
    }


    @Override
    public void displayBooks(ArrayList<Book> books) {
        super.displayBooks(books);
    }
}

class Student extends User {
    private String name;
    private String nim;
    private String faculty;
    private String programStudy;
    private ArrayList<Book> borrowedBooks;

    public Student(String name, String nim, String faculty, String programStudy) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.programStudy = programStudy;
        this.borrowedBooks = new ArrayList<>();
    }

    public void displayInfo() {
        System.out.println("Student Name: " + name);
        System.out.println("NIM: " + nim);
        System.out.println("Faculty: " + faculty);
        System.out.println("Program Study: " + programStudy);
    }

    public void showBorrowedBooks() {
        System.out.println("List of Borrowed Books:");
        for (Book book : borrowedBooks) {
            System.out.println("Title: " + book.getTitle());
        }
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void returnBooks(Book book) {
        borrowedBooks.remove(book);
        book.setStock(book.getStock() + 1);
        System.out.println("Book returned successfully.");
    }

    public void borrowBook(Book book, int days) {
        if (book.getStock() > 0) {
            borrowedBooks.add(book);
            book.setStock(book.getStock() - 1);
            System.out.println("Book borrowed successfully for " + days + " days.");
        } else {
            System.out.println("Sorry, the book is out of stock.");
        }
    }

    public void logout() {
        Scanner scanner = new Scanner(System.in);
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed. Logging out...");
        } else {
            System.out.println("You have borrowed books. Do you want to return them before logout? (yes/no)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                for (Book book : borrowedBooks) {
                    returnBooks(book);
                }
            }
            System.out.println("Logging out...");
        }
    }

    @Override
    public void displayBooks(ArrayList<Book> books) {
        super.displayBooks(books);
        System.out.println("To borrow a book, type 'borrow <Book ID>'");
    }
}


