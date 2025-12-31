package com.movieflix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.movieflix.model.Category;
import com.movieflix.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public void deleteCategoryById(Long id) {
        var category = findById(id);
        if (category == null) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

}
