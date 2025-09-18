package com.ttn.quizservice.repositories;

import com.ttn.quizservice.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long>{

}
