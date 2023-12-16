package com.joaogdantas.QuestionsOfDay.domain.model.data

data class CreateQuestionDTO(

    var imageUrl: String,

    var question: String,

    var alternatives: List<String>,

    var correctIndex: Int

)
