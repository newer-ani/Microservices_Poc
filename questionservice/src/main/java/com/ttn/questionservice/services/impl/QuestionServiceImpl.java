package com.ttn.questionservice.services.impl;

import com.ttn.questionservice.entities.Question;
import com.ttn.questionservice.repositories.QuestionRepository;
import com.ttn.questionservice.services.QuestionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    QuestionRepository questionRepo;
    RabbitTemplate rabbitTemplate;

    @Override
    public Question add(Question question) {
        Question savedQuestion =  questionRepo.save(question);
        String message;
        message = "New question added! ID: " + savedQuestion.getQuestionId()
                + ", Title: " + savedQuestion.getQuestion();
        log.info("going to enter the convert and send");
        rabbitTemplate.convertAndSend("question_exchange", "", message);
        log.info("publish message to rabbit mq successfully");
        return savedQuestion;
    }

    @Override
    public List<Question> addAll(List<Question> questions) {
        return questionRepo.saveAll(questions);
    }

    @Override
    public List<Question> getAllQuestion() {
        return questionRepo.findAll();
    }

    @Override
    @Cacheable(value="Question",key="#id")
    public Question getOne(Long id) {
        return questionRepo.findById(id).orElseThrow(()->new RuntimeException("Question not found"));
    }

    @Override
    @Cacheable(value="QuestionByQuiz",key="#quizId")
    public List<Question> getQuestionsOfQuiz(Long quizId) {
        return questionRepo.findByQuizId(quizId);
    }
}
