package com.example.librarymanager.service;

import com.example.librarymanager.dto.BookAuthorView;
import com.example.librarymanager.model.Author;
import com.example.librarymanager.model.Book;
import com.example.librarymanager.repository.AuthorRepository;
import com.example.librarymanager.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id " + id));
    }

    @Transactional(readOnly = true)
    public List<BookAuthorView> findBookAuthorViews() {
        return bookRepository.findBookAuthorViews();
    }

    public Book save(Book book) {
        book.setAuthor(resolveAuthor(book));
        return bookRepository.save(book);
    }

    public Book update(Long id, Book updatedBook) {
        Book existingBook = findById(id);
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setGenre(updatedBook.getGenre());
        existingBook.setPublicationYear(updatedBook.getPublicationYear());
        existingBook.setPrice(updatedBook.getPrice());
        existingBook.setAuthor(resolveAuthor(updatedBook));
        return bookRepository.save(existingBook);
    }

    private Author resolveAuthor(Book book) {
        if (book.getAuthor() == null || book.getAuthor().getId() == null) {
            throw new IllegalArgumentException("Choose an author for the book");
        }

        Long authorId = book.getAuthor().getId();
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id " + authorId));
    }
}

