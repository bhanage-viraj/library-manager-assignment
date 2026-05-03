package com.example.librarymanager.service;

import com.example.librarymanager.dto.BookAuthorView;
import com.example.librarymanager.model.Author;
import com.example.librarymanager.model.Book;
import com.example.librarymanager.repository.AuthorRepository;
import com.example.librarymanager.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void saveResolvesAuthorBeforePersistingBook() {
        Author selectedAuthor = new Author();
        selectedAuthor.setId(4L);
        selectedAuthor.setName("Noah Chen");

        Book book = new Book();
        book.setTitle("Spring Boot in Practice");
        book.setIsbn("978-1000000001");
        book.setGenre("Technology");
        book.setPublicationYear(2024);
        book.setPrice(new BigDecimal("39.99"));
        book.setAuthor(selectedAuthor);

        when(authorRepository.findById(4L)).thenReturn(Optional.of(selectedAuthor));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Book savedBook = bookService.save(book);

        assertThat(savedBook.getAuthor()).isSameAs(selectedAuthor);
        verify(bookRepository).save(book);
    }

    @Test
    void updateChangesBookFieldsAndAuthor() {
        Author newAuthor = new Author();
        newAuthor.setId(2L);
        newAuthor.setName("Arjun Mehta");

        Book existingBook = new Book();
        existingBook.setId(8L);
        existingBook.setTitle("Old Title");

        Book updatedBook = new Book();
        updatedBook.setTitle("Engineering Teams That Learn");
        updatedBook.setIsbn("978-1000000008");
        updatedBook.setGenre("Leadership");
        updatedBook.setPublicationYear(2022);
        updatedBook.setPrice(new BigDecimal("36.20"));
        updatedBook.setAuthor(newAuthor);

        when(bookRepository.findById(8L)).thenReturn(Optional.of(existingBook));
        when(authorRepository.findById(2L)).thenReturn(Optional.of(newAuthor));
        when(bookRepository.save(existingBook)).thenReturn(existingBook);

        Book savedBook = bookService.update(8L, updatedBook);

        assertThat(savedBook.getTitle()).isEqualTo("Engineering Teams That Learn");
        assertThat(savedBook.getAuthor()).isSameAs(newAuthor);
        verify(bookRepository).save(existingBook);
    }

    @Test
    void saveThrowsWhenSelectedAuthorDoesNotExist() {
        Author missingAuthor = new Author();
        missingAuthor.setId(123L);

        Book book = new Book();
        book.setAuthor(missingAuthor);

        when(authorRepository.findById(123L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.save(book))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Author not found");
    }

    @Test
    void returnsInnerJoinProjectionFromRepository() {
        BookAuthorView view = new BookAuthorView(
                1L,
                "Spring Boot in Practice",
                "978-1000000001",
                "Technology",
                2024,
                new BigDecimal("39.99"),
                4L,
                "Noah Chen",
                "noah.chen@example.com");
        when(bookRepository.findBookAuthorViews()).thenReturn(List.of(view));

        List<BookAuthorView> rows = bookService.findBookAuthorViews();

        assertThat(rows).hasSize(1);
        assertThat(rows.get(0).getAuthorName()).isEqualTo("Noah Chen");
    }
}

