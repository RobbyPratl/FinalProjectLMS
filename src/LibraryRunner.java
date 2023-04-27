import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LibraryRunner {
    // key = students reg number, value is list of isbn's borrowed by student
    // update file borrowed_books.txt when borrowed
    public static HashMap<String, List<String>> borrowedBooks = readFile("borrowed_books.txt");

    public static void main(String[] args) throws IOException {
        Library curLibrary = new Library("src/inventory_v2.txt");
        Scanner sc = new Scanner(System.in);
        while(true) {
            int option;
        System.out.println("Welcome to the Library! Select an option from the following:");
        System.out.print("1. Register\n2. Sort Books\n3. Search Books\n4. Borrow Book\n5. Return Book\n6. Show Inventory Stats\n");
        option = sc.nextInt();
            if (option == 1) {
                int size = 1;
                if (curLibrary.getStudents() != null) {
                    size = curLibrary.getStudents().size() + 1;
                }

                System.out.print("Please Enter your name");
                String name = sc.next();
                String regNumber = "";
                String initials = name.substring(0, 3).toUpperCase();
                String numericPart = String.format("%03d", size);
                regNumber = "54" + initials + numericPart;

                Student newStudent = new Student(name, regNumber);
                curLibrary.registerStudent(newStudent);

                System.out.println("Your registrationNumber: " + regNumber);
            }
            else if (option == 2) {
                System.out.println("Please enter a mode:\n1.Sort by ISBN\n2.Sort by Quantity");
                int mode = sc.nextInt();
                curLibrary.sort(mode);
                System.out.println("Sorted");
            }
            else if (option == 3){
                System.out.println("Please enter an ISBN to search: ");
                String isbn = sc.next();
                curLibrary.search(isbn);
            }
            else if (option == 4){
                System.out.println("Please enter a Registration Number to borrow: ");
                String regNumber = sc.next();

                System.out.println("Please enter an ISBN to borrow: ");
                String isbn = sc.next();

                curLibrary.lend(isbn);
                List<String> borrowedInst;

                if(borrowedBooks.get(regNumber) == null){
                    borrowedInst = new ArrayList<>();
                    borrowedInst.add(isbn);
                    borrowedBooks.put(regNumber,borrowedInst);
                }
                else {
                    borrowedInst = borrowedBooks.get(regNumber);
                    borrowedInst.add(isbn);
                    borrowedBooks.replace(regNumber, borrowedInst);
                }
                writeFile("src/borrowed_books.txt");

            }
            else if(option == 5){
                // return book
            }
            else if(option == 6){
                InventoryChart c = new InventoryChart();
                int fiction = curLibrary.getFictionBooks().size();
                int nonfiction = curLibrary.getNonfictionBooks().size();
                System.out.println(fiction);
                int[] stats = {fiction,nonfiction};
                c.setStats(stats);
                c.setTitle("Books");
                c.displayGraph();
            }
            else{
                System.out.println("Not a valid option, try again.");
            }

        }
    } // end main

    private static HashMap<String, List<String>> readFile(String filePath) {
        HashMap<String, List<String>> newMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String regNum = fields[0];
                String isbn = fields[1];
                isbn = isbn.substring(1, isbn.length() - 1);
                String[] isbnArrTemp = isbn.split(", ");
                List<String> isbnArr = Arrays.asList(isbnArrTemp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newMap;
    }

    public static void writeFile(String filePath) {
        // check if file already exists
        try (FileWriter borrowed_books = new FileWriter("borrowed_books.txt")) {
            borrowed_books.write("registrationNumber,isbn\n");

            for (String key : borrowedBooks.keySet()) {
                List<String> curBorrowed = borrowedBooks.get(key);
                String cur = key + "," + curBorrowed.toString() + "\n";
                borrowed_books.write(cur);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
