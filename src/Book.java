public abstract class Book {
    public String author;
    public String isbn;
    public String name;
    public String pages;

    public Book(String author, String isbn, String name, String pages) {
        this.author = author;
        this.isbn = isbn;
        this.name = name;
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
    public abstract String toString();
}
