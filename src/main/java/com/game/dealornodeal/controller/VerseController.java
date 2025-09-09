package com.game.dealornodeal.controller;

import com.game.dealornodeal.model.Verse;
import com.game.dealornodeal.service.VerseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verses")
public class VerseController {
    @Autowired
    private VerseService verseService;

    // CREATE
    @PostMapping
    public ResponseEntity<Verse> createVerse(@RequestBody Verse verse) {
        return ResponseEntity.ok(verseService.createVerse(verse));
    }

    // BULK CREATE
    @PostMapping("/bulk")
    public ResponseEntity<List<Verse>> createBulkVerses(@RequestBody List<Verse> verses) {
        return ResponseEntity.ok(verseService.createBulkVerses(verses));
    }

    // READ all
    @GetMapping
    public ResponseEntity<List<Verse>> getAllVerses() {
        return ResponseEntity.ok(verseService.getAllVerses());
    }

    // READ by id
    @GetMapping("/{id}")
    public ResponseEntity<Verse> getVerseById(@PathVariable Integer id) {
        return verseService.getVerseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // READ random
    @GetMapping("/random")
    public ResponseEntity<Verse> getRandomVerse() {
        return verseService.getRandomVerse()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Verse> updateVerse(@PathVariable Integer id, @RequestBody Verse verseDetails) {
        return verseService.updateVerse(id, verseDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVerse(@PathVariable Integer id) {
        boolean deleted = verseService.deleteVerse(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
