package com.library.controller;

import com.library.entity.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PostMapping("/upload")
    public Book uploadBook(
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("pdf") MultipartFile pdf) throws IOException {

        String uploadDir = System.getProperty("user.dir") + "/uploads/";

        File folder = new File(uploadDir);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + pdf.getOriginalFilename();

        File file = new File(uploadDir + fileName);

        pdf.transferTo(file);

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIssued(false);
        book.setPdfPath(fileName);

        return bookService.addBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id).orElse(null);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "Book Deleted Successfully";
    }

    @GetMapping("/search/{title}")
    public List<Book> searchBook(@PathVariable String title) {
        return bookService.searchBook(title);
    }

    @PutMapping("/issue/{id}")
    public Book issueBook(@PathVariable Long id) {
        return bookService.issueBook(id);
    }

    @PutMapping("/return/{id}")
    public Book returnBook(@PathVariable Long id) {
        return bookService.returnBook(id);
    }
}