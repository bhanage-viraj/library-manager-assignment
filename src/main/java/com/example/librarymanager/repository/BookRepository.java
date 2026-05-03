package com.example.librarymanager.repository;

import com.example.librarymanager.dto.BookAuthorView;
import com.example.librarymanager.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    @Query("""
            select new com.example.librarymanager.dto.BookAuthorView(
                b.id,
                b.title,
                b.isbn,
                b.genre,
                b.publicationYear,
                b.price,
                a.id,
                a.name,
                a.email
            )
            from Book b
            inner join b.author a
            order by a.name asc, b.title asc
            """)
    List<BookAuthorView> findBookAuthorViews();
}

