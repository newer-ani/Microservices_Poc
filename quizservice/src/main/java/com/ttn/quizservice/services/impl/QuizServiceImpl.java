package com.ttn.quizservice.services.impl;

import com.ttn.quizservice.entities.Quiz;
import com.ttn.quizservice.repositories.QuizRepository;
import com.ttn.quizservice.services.QuestionClient;
import com.ttn.quizservice.services.QuizService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class QuizServiceImpl implements QuizService {

    QuizRepository quizRepo;
    QuestionClient questionClient;

    @Override
    public Quiz add(Quiz quiz) {
        return quizRepo.save(quiz);
    }

    @Override
    public List<Quiz> addAll(List<Quiz> quiz) {
        return quizRepo.saveAll(quiz);
    }

    @Override
    public List<Quiz> getAllQuiz() {
        List<Quiz> quizzes = quizRepo.findAll();
        return quizzes.stream().map(quiz -> {quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
        return quiz;
        }).toList();
    }

    @Override
    public Quiz getOne(Long id) {
        Quiz quiz = quizRepo.findById(id).orElseThrow(()->new RuntimeException("Quiz not found"));
        quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
        return quiz;
    }
}
