import java.util.List;

public interface LibraryManagementSystem {
    public void inventory(String filePath);
    public void lend(String isbn);
    public void putBack(String isbn);
    public void registerStudent(Student student);
    public String search(String isbn);
    public List<Book> sort(int mode);

}
