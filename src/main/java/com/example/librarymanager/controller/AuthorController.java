package com.example.librarymanager.controller;

import com.example.librarymanager.model.Author;
import com.example.librarymanager.service.AuthorService;
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
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author());
        return prepareAuthorForm(model, "/authors", false);
    }

    @PostMapping
    public String createAuthor(
            @Valid @ModelAttribute("author") Author author,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return prepareAuthorForm(model, "/authors", false);
        }

        try {
            authorService.save(author);
            redirectAttributes.addFlashAttribute("successMessage", "Author created successfully.");
            return "redirect:/authors";
        } catch (DataIntegrityViolationException exception) {
            bindingResult.rejectValue("email", "duplicate", "This email is already used by another author.");
            return prepareAuthorForm(model, "/authors", false);
        }
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        return prepareAuthorForm(model, "/authors/" + id, true);
    }

    @PostMapping("/{id}")
    public String updateAuthor(
            @PathVariable Long id,
            @Valid @ModelAttribute("author") Author author,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        author.setId(id);

        if (bindingResult.hasErrors()) {
            return prepareAuthorForm(model, "/authors/" + id, true);
        }

        try {
            authorService.update(id, author);
            redirectAttributes.addFlashAttribute("successMessage", "Author updated successfully.");
            return "redirect:/authors";
        } catch (DataIntegrityViolationException exception) {
            bindingResult.rejectValue("email", "duplicate", "This email is already used by another author.");
            return prepareAuthorForm(model, "/authors/" + id, true);
        } catch (EntityNotFoundException exception) {
            bindingResult.reject("missingAuthor", exception.getMessage());
            return prepareAuthorForm(model, "/authors/" + id, true);
        }
    }

    private String prepareAuthorForm(Model model, String formAction, boolean editMode) {
        model.addAttribute("formAction", formAction);
        model.addAttribute("editMode", editMode);
        return "authors/form";
    }
}

