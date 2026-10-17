import java.util.Scanner;

public class SchoolManagementSystem {
    private static String[] studentNames;
    private static String[] studentRegNumbers;
    private static double[] studentMarks;
    private static String[] studentSubjects;
    private static String[] studentClasses;
    private static int registeredCount = 0;

    public static boolean login(Scanner scanner) {
        System.out.println("=== PRIME HIGH SCHOOL LOGIN ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if ("teacher".equals(username) && "admin123".equals(password)) {
            System.out.println("Login successful!\n");
            return true;
        } else {
            System.out.println("Invalid credentials. Access denied.\n");
            return false;
        }
    }

    public static void registerStudents(Scanner scanner) {
        System.out.print("Enter the number of students you want to register: ");
        int count = Integer.parseInt(scanner.nextLine());

        studentNames = new String[count];
        studentRegNumbers = new String[count];
        studentSubjects = new String[count];
        studentClasses = new String[count];
        studentMarks = new double[count];
        registeredCount = count;

        for (int i = 0; i < count; i++) {
            System.out.println("\nRegistering Student " + (i + 1) + ":");
            System.out.print("Enter Name: ");
            studentNames[i] = scanner.nextLine();
            System.out.print("Enter Registration Number: ");
            studentRegNumbers[i] = scanner.nextLine();
            System.out.print("Enter Subject: ");
            studentSubjects[i] = scanner.nextLine();
            System.out.print("Enter Class: ");
            studentClasses[i] = scanner.nextLine();
            studentMarks[i] = -1.0; 
        }
        System.out.println("\nAll students registered successfully!\n");
    }

    public static void recordMarks(Scanner scanner) {
        if (registeredCount == 0) {
            System.out.println("No students registered yet.\n");
            return;
        }

        System.out.println("=== RECORD STUDENTS' MARKS ===");
        for (int i = 0; i < registeredCount; i++) {
            System.out.print("Enter marks for " + studentNames[i] + " (" + studentRegNumbers[i] + "): ");
            studentMarks[i] = Double.parseDouble(scanner.nextLine());
        }
        System.out.println("Marks recorded successfully!\n");
    }

    public static void viewStudentsAndMarks() {
        if (registeredCount == 0) {
            System.out.println("No student records available.\n");
            return;
        }

        System.out.println("=== REGISTERED STUDENTS AND MARKS ===");
        System.out.printf("%-15s %-15s %-15s %-10s %-10s\n", "Reg No", "Name", "Subject", "Class", "Marks");
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < registeredCount; i++) {
            String marksStr = (studentMarks[i] == -1.0) ? "Not Recorded" : String.valueOf(studentMarks[i]);
            System.out.printf("%-15s %-15s %-15s %-10s %-10s\n",
                    studentRegNumbers[i], studentNames[i], studentSubjects[i], studentClasses[i], marksStr);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!login(scanner)) {
            scanner.close();
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("1. Register Students");
            System.out.println("2. Record Students' Marks");
            System.out.println("3. View All Registered Students & Marks");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    registerStudents(scanner);
                    break;
                case 2:
                    recordMarks(scanner);
                    break;
                case 3:
                    viewStudentsAndMarks();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.\n");
            }
        }
        scanner.close();
    }
}