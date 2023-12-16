package com.joaogdantas.QuestionsOfDay.domain.repository

import com.joaogdantas.QuestionsOfDay.domain.model.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional
import java.util.UUID

interface QuestionRepository : JpaRepository<Question, UUID> {
    fun findByQuestion(question: String): Optional<Question>

    @Query(
        value = "SELECT * FROM questions q ORDER BY RANDOM() LIMIT :limit",
        nativeQuery = true
    )
    fun findRandomQuestions(@Param("limit") limit: Int): List<Question>

}