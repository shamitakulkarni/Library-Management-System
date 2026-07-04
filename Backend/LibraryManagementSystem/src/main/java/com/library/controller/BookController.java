package com.library.controller;

import com.library.entity.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    private static final String UPLOAD_DIR = "uploads";

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadBook(
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("pdf") MultipartFile pdf) {

        try {

            if (pdf.isEmpty()) {
                return ResponseEntity.badRequest().body("Please select a PDF file.");
            }

            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = System.currentTimeMillis() + "_" + pdf.getOriginalFilename();

            Path filePath = uploadPath.resolve(fileName);

            Files.copy(
                    pdf.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setIssued(false);
            book.setPdfPath(fileName);

            Book savedBook = bookService.addBook(book);

            return ResponseEntity.ok(savedBook);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Upload failed: " + e.getMessage());
        }
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

    @GetMapping("/files/{filename}")
    public ResponseEntity<Resource> getPdfFile(@PathVariable String filename) {

        try {

            Path filePath = Paths.get(UPLOAD_DIR).resolve(filename).normalize();

            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
