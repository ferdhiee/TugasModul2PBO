class Book {
    private String bookId;
    private String title;
    private String author;
    private int stock;

    public Book(String bookId, String title, String author, int stock) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.stock = stock;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

class HistoryBook extends Book {
    public HistoryBook(String title, String author, int stock) {
        super("HIS" + (int) (Math.random() * 1000), title, author, stock);
    }
}

class StoryBook extends Book {
    public StoryBook(String title, String author, int stock) {
        super("STO" + (int) (Math.random() * 1000), title, author, stock);
    }
}

class TextBook extends Book {
    public TextBook(String title, String author, int stock) {
        super("TXT" + (int) (Math.random() * 1000), title, author, stock);
    }
}
