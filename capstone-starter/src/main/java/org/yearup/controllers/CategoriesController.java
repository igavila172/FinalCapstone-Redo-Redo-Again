package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private CategoryDao categoryDao;

    // GET /categories
    @GetMapping
    public List<Category> getAll() {
        return categoryDao.getAllCategories();
    }

    // GET /categories/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable int id) {
        Category category = categoryDao.getById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    // POST /categories (Admin only)
    @PostMapping
    public Category add(@RequestBody Category category) {
        // TODO: check if user is admin
        return categoryDao.create(category);
    }

    // PUT /categories/{id} (Admin only)
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable int id, @RequestBody Category category) {
        // TODO: check if user is admin
        Category updated = categoryDao.update(id, category);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // DELETE /categories/{id} (Admin only)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        // TODO: check if user is admin
        categoryDao.delete(id);
        return ResponseEntity.noContent().build();
    }
}
