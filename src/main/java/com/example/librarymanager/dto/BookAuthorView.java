package com.example.librarymanager.dto;

import java.math.BigDecimal;

public class BookAuthorView {

    private final Long bookId;
    private final String bookTitle;
    private final String isbn;
    private final String genre;
    private final Integer publicationYear;
    private final BigDecimal price;
    private final Long authorId;
    private final String authorName;
    private final String authorEmail;

    public BookAuthorView(
            Long bookId,
            String bookTitle,
            String isbn,
            String genre,
            Integer publicationYear,
            BigDecimal price,
            Long authorId,
            String authorName,
            String authorEmail) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.isbn = isbn;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.price = price;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }
}

