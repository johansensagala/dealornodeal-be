package com.game.dealornodeal.controller;

import com.game.dealornodeal.model.Copper;
import com.game.dealornodeal.service.CopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coppers")
public class CopperController {

    @Autowired
    private CopperService copperService;

    // CREATE
    @PostMapping
    public ResponseEntity<Copper> createCopper(@RequestBody Copper copper) {
        Copper savedCopper = copperService.createCopper(copper);
        return ResponseEntity.ok(savedCopper);
    }

    // BULK CREATE
    @PostMapping("/bulk")
    public ResponseEntity<List<Copper>> createBulkCoppers(@RequestBody List<Copper> coppers) {
        List<Copper> savedCoppers = copperService.createBulkCoppers(coppers);
        return ResponseEntity.ok(savedCoppers);
    }

    // READ all
    @GetMapping
    public ResponseEntity<List<Copper>> getAllCoppers() {
        return ResponseEntity.ok(copperService.getAllCoppers());
    }

    // READ by id
    @GetMapping("/{id}")
    public ResponseEntity<Copper> getCopperById(@PathVariable Integer id) {
        return copperService.getCopperById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Copper> updateCopper(@PathVariable Integer id, @RequestBody Copper copperDetails) {
        return copperService.updateCopper(id, copperDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCopper(@PathVariable Integer id) {
        boolean deleted = copperService.deleteCopper(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
