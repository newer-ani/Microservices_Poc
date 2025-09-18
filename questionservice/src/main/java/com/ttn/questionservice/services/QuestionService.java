package com.ttn.questionservice.services;

import com.ttn.questionservice.entities.Question;

import java.util.List;

public interface QuestionService{

    Question add(Question question);
    List<Question> addAll(List<Question> questions);
    List<Question> getAllQuestion();
    Question getOne(Long id);
    List<Question> getQuestionsOfQuiz(Long quizId);
}
