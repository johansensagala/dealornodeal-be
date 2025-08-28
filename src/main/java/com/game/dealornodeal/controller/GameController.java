package com.game.dealornodeal.controller;

import com.game.dealornodeal.dto.request.GameRequest;
import com.game.dealornodeal.dto.response.GameResponse;
import com.game.dealornodeal.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<GameResponse> createGame(@RequestBody GameRequest request) {
        return ResponseEntity.ok(gameService.createGame(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameResponse> updateGame(@PathVariable Integer id, @RequestBody GameRequest request) {
        return ResponseEntity.ok(gameService.updateGame(id, request));
    }

    @GetMapping
    public ResponseEntity<List<GameResponse>> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> getGameById(@PathVariable Integer id) {
        return ResponseEntity.ok(gameService.getGameById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Integer id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}
