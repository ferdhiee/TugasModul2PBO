import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        LibrarySystem librarySystem = new LibrarySystem();
        librarySystem.menu();
    }
}

class LibrarySystem {
    ArrayList<Book> bookList = new ArrayList<>();
    ArrayList<Student> userStudents = new ArrayList<>();

    void menu() {
        System.out.println("=== Perpustakaan ===");
        System.out.println("1. Login sebagai Admin");
        System.out.println("2. Login sebagai Student");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            Admin admin = new Admin(this);
            admin.menuAdmin();
        } else if (choice == 2) {
            Student student = new Student(this);
            student.menuStudent();
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    void displayBooks() {
        System.out.println("=== Daftar Buku ===");
        for (Book book : bookList) {
            System.out.println("ID: " + book.id_buku + ", Judul: " + book.judul + ", Author: " + book.author + ", Stok: " + book.stok);
        }
    }

    void addBook(Book book) {
        bookList.add(book);
    }

    void removeBook(int bookId) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).id_buku == bookId) {
                bookList.remove(i);
                System.out.println("Buku berhasil dihapus.");
                return;
            }
        }
        System.out.println("Buku dengan ID tersebut tidak ditemukan.");
    }

    void addStudent(Student student) {
        userStudents.add(student);
    }

    void displayStudents() {
        System.out.println("=== Daftar Mahasiswa ===");
        for (Student student : userStudents) {
            System.out.println("Nama: " + student.nama + ", NIM: " + student.nim + ", Fakultas: " + student.fakultas + ", Program Studi: " + student.prodi);
        }
    }
}

class Book {
    int id_buku;
    String judul;
    String author;
    int stok;

    public Book(int id_buku, String judul, String author, int stok) {
        this.id_buku = id_buku;
        this.judul = judul;
        this.author = author;
        this.stok = stok;
    }
}

class User {
    String nama;

    public User(String nama) {
        this.nama = nama;
    }
}

class Student extends User {
    String nim;
    String fakultas;
    String prodi;
    LibrarySystem librarySystem;

    public Student(LibrarySystem librarySystem) {
        super("Nama Student");
        this.librarySystem = librarySystem;
    }

    public Student(String nama, String nim, String fakultas, String prodi) {
        super(nama);
        this.nim = nim;
        this.fakultas = fakultas;
        this.prodi = prodi;
    }

    public String getNim() {
        return nim;
    }

    void menuStudent() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Menu Mahasiswa ===");
            System.out.println("1. Daftar Buku");
            System.out.println("2. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    librarySystem.displayBooks();
                    break;
                case 2:
                    logout();
                    return; // Keluar dari metode menuStudent
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    void logout() {
        System.out.println("Logout berhasil.");
        librarySystem.menu(); // Kembali ke menu utama setelah logout
    }
}

class Admin extends User {
    LibrarySystem librarySystem;

    public Admin(LibrarySystem librarySystem) {
        super("Admin");
        this.librarySystem = librarySystem;
    }

    void menuAdmin() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Dashboard Admin ===");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Tampilkan Daftar Mahasiswa");
            System.out.println("3. Tambah Buku");
            System.out.println("4. Hapus Buku");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    librarySystem.displayStudents();
                    break;
                case 3:
                    addBook();
                    break;
                case 4:
                    removeBook();
                    break;
                case 5:
                    logout();
                    return; // Keluar dari metode menuAdmin
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukkan nama mahasiswa: ");
        String nama = scanner.nextLine();
        System.out.println("Masukkan NIM mahasiswa: ");
        String nim = scanner.nextLine();
        System.out.println("Masukkan fakultas mahasiswa: ");
        String fakultas = scanner.nextLine();
        System.out.println("Masukkan program studi mahasiswa: ");
        String prodi = scanner.nextLine();

        if (nim.length() != 15) {
            System.out.println("NIM tidak valid.");
            return;
        }

        Student student = new Student(nama, nim, fakultas, prodi);
        librarySystem.addStudent(student);
        System.out.println("Mahasiswa berhasil ditambahkan.");
    }

    void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukkan ID buku: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Masukkan judul buku: ");
        String judul = scanner.nextLine();
        System.out.println("Masukkan nama penulis: ");
        String author = scanner.nextLine();
        System.out.println("Masukkan stok buku: ");
        int stok = scanner.nextInt();

        Book book = new Book(id, judul, author, stok);
        librarySystem.addBook(book);
        System.out.println("Buku berhasil ditambahkan.");
    }

    void removeBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukkan ID buku yang akan dihapus: ");
        int id = scanner.nextInt();

        librarySystem.removeBook(id);
    }

    void logout() {
        System.out.println("Logout berhasil.");
        librarySystem.menu(); // Kembali ke menu utama setelah logout
    }
}
