package com.releaseops.controller;

import com.releaseops.model.Release;
import com.releaseops.repository.ReleaseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/releases")
@CrossOrigin(origins = "*")
public class ReleaseController {

    private final ReleaseRepository repository;

    public ReleaseController(ReleaseRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Release> all() {
        return repository.findAll();
    }

    @PostMapping
    public Release create(@RequestBody Release release) {
        return repository.save(release);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Release> get(@PathVariable Long id) {
        Optional<Release> r = repository.findById(id);
        if (r.isPresent()) return ResponseEntity.ok(r.get());
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Release> update(@PathVariable Long id, @RequestBody Release updated) {
        return repository.findById(id).map(r -> {
            r.setTitle(updated.getTitle());
            r.setDescription(updated.getDescription());
            r.setStatus(updated.getStatus());
            r.setAssignedEngineer(updated.getAssignedEngineer());
            repository.save(r);
            return ResponseEntity.ok(r);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
