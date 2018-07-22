package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.domain.Book;
import pl.coderslab.domain.MemoryBookService;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    MemoryBookService memoryBookService;

    @RequestMapping("")
    public List<Book> showBooks() {
        return memoryBookService.getBookList();
    }

    @GetMapping("{id}")
    public Book getBook (@PathVariable long id){
        Book book = memoryBookService.getBook(id);
        return book;
    }

    @DeleteMapping("{id}")
    public void delBook(@PathVariable long id){
        memoryBookService.delBook(id);

    }

    @PostMapping("/")
    public void addBook(@RequestBody Book book){
        String isbn = book.getIsbn();
        String title = book.getTitle();
        String publisher = book.getPublisher();
        String type = book.getType();
        String author = book.getAuthor();
        memoryBookService.addBook(isbn, title, author, publisher, type);
    }

    @PutMapping("/{id}")
    public void editBook(@PathVariable long id, @RequestBody Book book) {
        Book bookToEdit = memoryBookService.getBook(id);
        bookToEdit.setTitle(book.getTitle());
        bookToEdit.setAuthor(book.getAuthor());
        bookToEdit.setPublisher(book.getPublisher());
        bookToEdit.setType(book.getType());
        bookToEdit.setIsbn(book.getIsbn());

    }
}
