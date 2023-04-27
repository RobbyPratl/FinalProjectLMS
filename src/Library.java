import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class Library implements LibraryManagementSystem{
    private HashMap<String, Integer> inventory = new HashMap<String,Integer>(); // ISBN : Quantity
    private List<FictionBook> fictionBooks = new ArrayList<>();
    private List<NonfictionBook> nonfictionBooks = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    Library(String pathToInventory){
        this.inventory(pathToInventory);
        this.inventory = inventory;
        this.nonfictionBooks = nonfictionBooks;
        this.students = students;
        this.fictionBooks = fictionBooks;
    }
    public int[] availableBooks(){return new int[0];}

    public List<FictionBook> getFictionBooks() {
        return fictionBooks;
    }

    public void setInventory(HashMap<String, Integer> inventory) {
        this.inventory = inventory;
    }

    public void setFictionBooks(List<FictionBook> fictionBooks) {
        this.fictionBooks = fictionBooks;
    }

    public void setNonfictionBooks(List<NonfictionBook> nonfictionBooks) {
        this.nonfictionBooks = nonfictionBooks;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public HashMap<String, Integer> getInventory() {
        return new HashMap<String, Integer>();
    }
    public List<NonfictionBook> getNonfictionBooks() {
        return this.nonfictionBooks;
    }


    public List<Student> getStudents() {
        return students;
    }


    @Override
    public void inventory(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // reading header line to avoid error
            while ((line = br.readLine()) != null) {
                String[] bookData = line.split(",");
                String isbn = bookData[0];
                String name = bookData[1];
                String author = bookData[2];
                String pages = bookData[3];
                String quantity = bookData[4];
                String type = bookData[5];
                if (type.equals("fiction")) {
                    FictionBook fictionBook = new FictionBook(isbn, name, author, pages);
                    this.fictionBooks.add(fictionBook);
                } else {
                    NonfictionBook nonfictionBook = new NonfictionBook(isbn, name, author, pages);
                    this.nonfictionBooks.add(nonfictionBook);
                }
                this.inventory.put(isbn, Integer.valueOf(quantity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lend(String isbn) {
        if(this.inventory.containsKey(isbn)){
            if(this.inventory.get(isbn) == 0){
                System.out.println("Sorry all available copy are taken :(");
            }else {
                int n = this.inventory.get(isbn);
                this.inventory.replace(isbn,n-1);
                System.out.println("Book borrowed successfully");
                // need to write to borrowed_books.txt in LibraryRunner
            }
        }else{
            System.out.println("Book not in database");
        }
    }

    @Override
    public void putBack(String isbn) {

    }

    @Override
    public void registerStudent(Student student) {

    }

    @Override
    public String search(String isbn) {
        return null;
    }

    @Override
    public List<Book> sort(int mode) {
        return List.of(new Book[0]);
    }

}
