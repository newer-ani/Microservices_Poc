package com.ttn.quizservice.controllers;

import com.ttn.quizservice.entities.Quiz;
import com.ttn.quizservice.services.QuizService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class QuizController {

    QuizService quizService;

    //create
    @PostMapping
    public Quiz create(@RequestBody Quiz quiz) {
        return quizService.add(quiz);
    }

    @PostMapping("/add-all")
    public ResponseEntity<List<Quiz>> addQuestions(@RequestBody List<Quiz> questions) {
        List<Quiz> savedQuestions = quizService.addAll(questions);
        return ResponseEntity.ok(savedQuestions);
    }

    //get all
    @GetMapping
    public List<Quiz> get() {
        return quizService.getAllQuiz();
    }

    @GetMapping("/{id}")
    public Quiz getOne(@PathVariable Long id) {
        return quizService.getOne(id);
    }

}
