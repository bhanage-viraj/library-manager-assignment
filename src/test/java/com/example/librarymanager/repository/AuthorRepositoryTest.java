package com.example.librarymanager.repository;

import com.example.librarymanager.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void loadsSeededAuthors() {
        assertThat(authorRepository.findAll()).hasSize(10);
    }

    @Test
    void findsAuthorByEmail() {
        Optional<Author> author = authorRepository.findByEmail("maya.hart@example.com");

        assertThat(author).isPresent();
        assertThat(author.get().getName()).isEqualTo("Maya Hart");
    }

    @Test
    void savesNewAuthor() {
        Author author = new Author();
        author.setName("Nora Field");
        author.setEmail("nora.field@example.com");
        author.setCountry("Ireland");
        author.setBiography("Writes about pragmatic software design.");

        Author savedAuthor = authorRepository.save(author);

        assertThat(savedAuthor.getId()).isNotNull();
        assertThat(authorRepository.findByEmail("nora.field@example.com")).isPresent();
    }
}

