package com.example.librarymanager.service;

import com.example.librarymanager.model.Author;
import com.example.librarymanager.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void findAllReturnsRepositoryData() {
        Author author = new Author();
        author.setName("Maya Hart");
        when(authorRepository.findAll()).thenReturn(List.of(author));

        List<Author> authors = authorService.findAll();

        assertThat(authors).hasSize(1);
        assertThat(authors.get(0).getName()).isEqualTo("Maya Hart");
    }

    @Test
    void updateCopiesEditableFields() {
        Author existingAuthor = new Author();
        existingAuthor.setId(1L);
        existingAuthor.setName("Old Name");
        existingAuthor.setEmail("old@example.com");
        existingAuthor.setCountry("Canada");

        Author updatedAuthor = new Author();
        updatedAuthor.setName("New Name");
        updatedAuthor.setEmail("new@example.com");
        updatedAuthor.setCountry("United States");
        updatedAuthor.setBiography("Updated biography.");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(existingAuthor));
        when(authorRepository.save(existingAuthor)).thenReturn(existingAuthor);

        Author savedAuthor = authorService.update(1L, updatedAuthor);

        assertThat(savedAuthor.getName()).isEqualTo("New Name");
        assertThat(savedAuthor.getEmail()).isEqualTo("new@example.com");
        assertThat(savedAuthor.getCountry()).isEqualTo("United States");
        verify(authorRepository).save(existingAuthor);
    }

    @Test
    void findByIdThrowsWhenAuthorDoesNotExist() {
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authorService.findById(99L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Author not found");
    }
}

