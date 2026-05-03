package com.example.librarymanager.repository;

import com.example.librarymanager.dto.BookAuthorView;
import com.example.librarymanager.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void loadsSeededBooks() {
        assertThat(bookRepository.findAll()).hasSize(10);
    }

    @Test
    void findsBookByIsbn() {
        Optional<Book> book = bookRepository.findByIsbn("978-1000000001");

        assertThat(book).isPresent();
        assertThat(book.get().getTitle()).isEqualTo("Spring Boot in Practice");
    }

    @Test
    void innerJoinQueryReturnsBookAuthorProjection() {
        List<BookAuthorView> rows = bookRepository.findBookAuthorViews();

        assertThat(rows).hasSize(10);
        assertThat(rows)
                .extracting(BookAuthorView::getAuthorName)
                .contains("Noah Chen", "Maya Hart");
        assertThat(rows)
                .extracting(BookAuthorView::getBookTitle)
                .contains("Spring Boot in Practice", "Readable Systems");
    }
}

