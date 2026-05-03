package com.example.librarymanager.service;

import com.example.librarymanager.model.Author;
import com.example.librarymanager.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Author findById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id " + id));
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public Author update(Long id, Author updatedAuthor) {
        Author existingAuthor = findById(id);
        existingAuthor.setName(updatedAuthor.getName());
        existingAuthor.setEmail(updatedAuthor.getEmail());
        existingAuthor.setCountry(updatedAuthor.getCountry());
        existingAuthor.setBiography(updatedAuthor.getBiography());
        return authorRepository.save(existingAuthor);
    }
}

