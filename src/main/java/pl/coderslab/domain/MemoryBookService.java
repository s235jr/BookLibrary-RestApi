package pl.coderslab.domain;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class MemoryBookService {

    private List<Book> bookList;

    public MemoryBookService() {
        this.bookList = new ArrayList<>();
        bookList.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel",
                "Helion", "programming"));
        bookList.add(new Book(2L, "9788324627738", "Rusz glowa, Java.",
                "Sierra Kathy, Bates Bert", "Helion", "programming"));
        bookList.add(new Book(3L, "9780130819338", "Java 2. Podstawy",
                "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public Book getBook(long id) {

        Book book = getBookList().stream().filter(s -> s.getId() == id).findAny().get();
        return book;
    }

    public void editBook(long id, String isbn, String title, String author, String publisher, String type) {

        Book book = getBookList().stream().filter(s -> s.getId() == id).findAny().get();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setType(type);

    }

    public void addBook(String isbn, String title, String author, String publisher, String type) {
        Book book = new Book();
        book.setId(Book.getFirstFreeId());
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setType(type);
        System.out.println("To książka którą dodaje do listy" + book.toString());
        this.bookList.add(book);

    }

    public void delBook(long id) {
        List<Book> collect = getBookList().stream().filter(s -> s.getId() != id).collect(Collectors.toList());
        this.bookList = collect;
    }
}
