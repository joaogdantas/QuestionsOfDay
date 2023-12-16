package com.joaogdantas.QuestionsOfDay.domain.model.data

data class QuestionsOfDayDTO(
    var imageUrl: String,

    var question: String,

    var alternatives: List<String>
)