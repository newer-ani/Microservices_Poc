package com.ttn.questionservice.controllers;

import com.ttn.questionservice.entities.Question;
import com.ttn.questionservice.services.QuestionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class QuestionController {

    QuestionService questionService;

    //    create
    @PostMapping
    public Question create(@RequestBody Question question) {
        return questionService.add(question);
    }

    @PostMapping("/add-all")
    public ResponseEntity<List<Question>> addQuestions(@RequestBody List<Question> questions) {
        List<Question> savedQuestions = questionService.addAll(questions);
        return ResponseEntity.ok(savedQuestions);
    }

    //    get all
    @GetMapping
    public List<Question> getAll() {
        return questionService.getAllQuestion();
    }

    @GetMapping("/{questionId}")
    public Question getAll(@PathVariable Long questionId) {
        return questionService.getOne(questionId);
    }

//    get all question of specific quiz
    @GetMapping("/quiz/{quizId}")
    public List<Question> getQuestionsOfQuiz(@PathVariable Long quizId) {
        return questionService.getQuestionsOfQuiz(quizId);
    }

}
