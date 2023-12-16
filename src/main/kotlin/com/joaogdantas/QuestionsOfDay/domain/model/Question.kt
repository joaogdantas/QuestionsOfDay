package com.joaogdantas.QuestionsOfDay.domain.model

import com.joaogdantas.QuestionsOfDay.domain.model.data.CreateQuestionDTO
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity(name = "question")
@Table(name = "questions")
class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    val id: UUID? = null

    @Column(name = "image_url")
    var imageUrl: String? = null

    @Column(name = "question")
    var question: String? = null

    @Column(name = "alternatives")
    var alternatives: List<String>? = null

    @Column(name = "correct_index")
    var correctIndex: Int? = null

    constructor(data: CreateQuestionDTO) {
        this.imageUrl = data.imageUrl
        this.question = data.question
        this.alternatives = data.alternatives
        this.correctIndex = data.correctIndex
    }
}