package com.library.service;

import com.library.entity.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBook(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public Book issueBook(Long id) {

        Book book = bookRepository.findById(id).orElseThrow();

        book.setIssued(true);

        return bookRepository.save(book);
    }

    public Book returnBook(Long id) {

        Book book = bookRepository.findById(id).orElseThrow();

        book.setIssued(false);

        return bookRepository.save(book);
    }
}