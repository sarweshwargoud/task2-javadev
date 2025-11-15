// StudentManagement.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class StudentManagement {

    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewAllStudents(); break;
                case 3: updateStudent(); break;
                case 4: deleteStudent(); break;
                case 5: searchStudent(); break;
                case 6: sortByMarksDescending(); break;
                case 7:
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n===== STUDENT RECORD MANAGEMENT =====");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Search Student by ID");
        System.out.println("6. Sort by Marks (Descending)");
        System.out.println("7. Exit");
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int val = Integer.parseInt(sc.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double val = Double.parseDouble(sc.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static void addStudent() {
        System.out.println("\n--- Add Student ---");
        int id = readInt("Enter ID: ");
        if (findById(id) != null) {
            System.out.println("A student with this ID already exists. Use a different ID.");
            return;
        }
        String name = readLine("Enter name: ");
        double marks = readDouble("Enter marks (0-100): ");
        Student s = new Student(id, name, marks);
        students.add(s);
        System.out.println("Student added successfully.");
    }

    private static void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void updateStudent() {
        System.out.println("\n--- Update Student ---");
        int id = readInt("Enter ID to update: ");
        Student s = findById(id);
        if (s == null) {
            System.out.println("No student found with ID " + id);
            return;
        }
        String newName = readLine("Enter new name (leave blank to keep '" + s.getName() + "'): ");
        if (!newName.isEmpty()) s.setName(newName);

        String markInput = readLine("Enter new marks (leave blank to keep " + s.getMarks() + "): ");
        if (!markInput.isEmpty()) {
            try {
                double newMarks = Double.parseDouble(markInput);
                s.setMarks(newMarks);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Marks not changed.");
            }
        }
        System.out.println("Student updated: " + s);
    }

    private static void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        int id = readInt("Enter ID to delete: ");
        Student s = findById(id);
        if (s == null) {
            System.out.println("No student found with ID " + id);
            return;
        }
        students.remove(s);
        System.out.println("Student with ID " + id + " deleted.");
    }

    private static void searchStudent() {
        System.out.println("\n--- Search Student ---");
        int id = readInt("Enter ID to search: ");
        Student s = findById(id);
        if (s == null) {
            System.out.println("No student found with ID " + id);
        } else {
            System.out.println("Found: " + s);
        }
    }

    private static void sortByMarksDescending() {
        if (students.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                return Double.compare(b.getMarks(), a.getMarks()); // descending
            }
        });
        System.out.println("Students sorted by marks (descending). Use 'View All' to see.");
    }

    private static Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }
}
