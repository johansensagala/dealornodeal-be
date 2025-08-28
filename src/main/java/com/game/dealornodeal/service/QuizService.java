package com.game.dealornodeal.service;

import com.game.dealornodeal.model.Quiz;
import com.game.dealornodeal.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    // CREATE
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    // BULK CREATE
    public List<Quiz> createBulkQuizzes(List<Quiz> quizzes) {
        return quizRepository.saveAll(quizzes);
    }

    // READ all
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    // READ by id
    public Optional<Quiz> getQuizById(Integer id) {
        return quizRepository.findById(id);
    }

    // READ random
    public Optional<Quiz> getRandomQuiz() {
        List<Quiz> quizzes = quizRepository.findAll();
        if (quizzes.isEmpty()) {
            return Optional.empty();
        }
        int randomIndex = (int) (Math.random() * quizzes.size());
        return Optional.of(quizzes.get(randomIndex));
    }

    // UPDATE
    public Optional<Quiz> updateQuiz(Integer id, Quiz quizDetails) {
        return quizRepository.findById(id).map(quiz -> {
            quiz.setQuestion(quizDetails.getQuestion());
            quiz.setAnswer(quizDetails.getAnswer());
            quiz.setType(quizDetails.getType());
            quiz.setLevel(quizDetails.getLevel());
            return quizRepository.save(quiz);
        });
    }

    // DELETE
    public boolean deleteQuiz(Integer id) {
        return quizRepository.findById(id).map(quiz -> {
            quizRepository.delete(quiz);
            return true;
        }).orElse(false);
    }
}
