package com.ttn.quizservice.services;

import com.ttn.quizservice.entities.Quiz;

import java.util.List;

public interface QuizService {

    Quiz add(Quiz quiz);

    List<Quiz> addAll(List<Quiz> quiz);

    List<Quiz> getAllQuiz();

    Quiz getOne(Long id);

}
