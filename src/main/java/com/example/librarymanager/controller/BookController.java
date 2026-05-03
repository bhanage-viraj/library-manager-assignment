package com.example.librarymanager.controller;

import com.example.librarymanager.model.Author;
import com.example.librarymanager.model.Book;
import com.example.librarymanager.service.AuthorService;
import com.example.librarymanager.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("bookAuthorViews", bookService.findBookAuthorViews());
        return "books/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        Book book = new Book();
        book.setAuthor(new Author());
        model.addAttribute("book", book);
        return prepareBookForm(model, "/books", false);
    }

    @PostMapping
    public String createBook(
            @Valid @ModelAttribute("book") Book book,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        validateAuthorSelection(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return prepareBookForm(model, "/books", false);
        }

        try {
            bookService.save(book);
            redirectAttributes.addFlashAttribute("successMessage", "Book created successfully.");
            return "redirect:/books";
        } catch (DataIntegrityViolationException exception) {
            bindingResult.rejectValue("isbn", "duplicate", "This ISBN is already assigned to another book.");
            return prepareBookForm(model, "/books", false);
        } catch (IllegalArgumentException | EntityNotFoundException exception) {
            bindingResult.rejectValue("author.id", "invalidAuthor", exception.getMessage());
            return prepareBookForm(model, "/books", false);
        }
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return prepareBookForm(model, "/books/" + id, true);
    }

    @PostMapping("/{id}")
    public String updateBook(
            @PathVariable Long id,
            @Valid @ModelAttribute("book") Book book,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        book.setId(id);
        validateAuthorSelection(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return prepareBookForm(model, "/books/" + id, true);
        }

        try {
            bookService.update(id, book);
            redirectAttributes.addFlashAttribute("successMessage", "Book updated successfully.");
            return "redirect:/books";
        } catch (DataIntegrityViolationException exception) {
            bindingResult.rejectValue("isbn", "duplicate", "This ISBN is already assigned to another book.");
            return prepareBookForm(model, "/books/" + id, true);
        } catch (IllegalArgumentException | EntityNotFoundException exception) {
            bindingResult.rejectValue("author.id", "invalidAuthor", exception.getMessage());
            return prepareBookForm(model, "/books/" + id, true);
        }
    }

    private String prepareBookForm(Model model, String formAction, boolean editMode) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("formAction", formAction);
        model.addAttribute("editMode", editMode);
        return "books/form";
    }

    private void validateAuthorSelection(Book book, BindingResult bindingResult) {
        if (book.getAuthor() == null || book.getAuthor().getId() == null) {
            bindingResult.rejectValue("author.id", "required", "Choose an author.");
        }
    }
}
