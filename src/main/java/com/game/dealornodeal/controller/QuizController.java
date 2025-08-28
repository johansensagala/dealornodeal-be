package com.game.dealornodeal.controller;

import com.game.dealornodeal.model.Quiz;
import com.game.dealornodeal.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    @Autowired
    private QuizService quizService;

    // CREATE
    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        Quiz savedQuiz = quizService.createQuiz(quiz);
        return ResponseEntity.ok(savedQuiz);
    }

    // BULK CREATE
    @PostMapping("/bulk")
    public ResponseEntity<List<Quiz>> createBulkQuizzes(@RequestBody List<Quiz> quizzes) {
        List<Quiz> savedQuizzes = quizService.createBulkQuizzes(quizzes);
        return ResponseEntity.ok(savedQuizzes);
    }

    // READ all
    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    // READ by id
    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Integer id) {
        return quizService.getQuizById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // READ random
    @GetMapping("/random")
    public ResponseEntity<Quiz> getRandomQuiz() {
        return quizService.getRandomQuiz()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Integer id, @RequestBody Quiz quizDetails) {
        return quizService.updateQuiz(id, quizDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Integer id) {
        boolean deleted = quizService.deleteQuiz(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
