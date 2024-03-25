import java.util.Scanner;
public class

class LibrarySystem {
    private final String adminUsername = "admin";
    private final String adminPassword = "admin";
    private final String mahasiswaNIM = "202310370311319";

    public boolean loginAdmin(String username, String password) {
        return username.equals(adminUsername) && password.equals(adminPassword);
    }

    public boolean loginMahasiswa(String nim) {
        return nim.length() == 15 && nim.equals(mahasiswaNIM);
    }
}

public class Tugas {
    public static void main(String[] args) {
        LibrarySystem librarySystem = new LibrarySystem();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Login sebagai admin");
            System.out.println("2. Login sebagai mahasiswa");
            System.out.println("3. Keluar");
            System.out.print("Pilih: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan username: ");
                    String adminUsername = scanner.next();
                    System.out.print("Masukkan password: ");
                    String adminPassword = scanner.next();
                    if (librarySystem.loginAdmin(adminUsername, adminPassword)) {
                        System.out.println("Login berhasil sebagai admin");
                    } else {
                        System.out.println("Login gagal");
                    }
                    break;
                case 2:
                    System.out.print("Masukkan NIM: ");
                    String nim = scanner.next();
                    if (librarySystem.loginMahasiswa(nim)) {
                        System.out.println("Login berhasil sebagai mahasiswa");
                    } else {
                        System.out.println("Login gagal");
                    }
                    break;
                case 3:
                    System.out.println("Keluar dari program");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
                    break;
            }
        } while (choice != 3);
    }
}
