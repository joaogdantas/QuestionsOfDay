package com.joaogdantas.QuestionsOfDay.controller

import com.joaogdantas.QuestionsOfDay.domain.model.Question
import com.joaogdantas.QuestionsOfDay.domain.model.data.CreateQuestionDTO
import com.joaogdantas.QuestionsOfDay.domain.model.data.QuestionsOfDayDTO
import com.joaogdantas.QuestionsOfDay.domain.repository.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/questions")
class QuestionController {

    @Autowired
    private lateinit var questionRepository: QuestionRepository

    @PostMapping("/create")
    fun createQuestion(@RequestBody data: CreateQuestionDTO,
                       uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<String> {

        val newQuestion = Question(data)
        questionRepository.save(newQuestion)

        val uri = uriComponentsBuilder
            .path("/questions/{id}")
            .buildAndExpand(newQuestion.id)
            .toUri()

        return ResponseEntity.status(HttpStatus.CREATED.value()).body("Questão criada com sucesso")
    }

    @GetMapping("/all")
    fun getAllQuestions(): ResponseEntity<List<Question>> {
        val questions = questionRepository.findAll()
        return ResponseEntity.ok(questions)
    }

    @GetMapping("/questionsOfDay")
    fun getRandomQuestions(): ResponseEntity<List<QuestionsOfDayDTO>> {
        val randomQuestions = questionRepository.findRandomQuestions(3)

        val questionsDTO = randomQuestions.map { question ->
            QuestionsOfDayDTO(
                imageUrl = question.imageUrl ?: "",
                question = question.question ?: "",
                alternatives = question.alternatives ?: emptyList()
            )
        }
        return ResponseEntity.ok(questionsDTO)
    }

    @GetMapping("/{questionId}/answer")
    fun checkAnswer(
        @PathVariable questionId: UUID,
        @RequestParam answer: Int
    ): ResponseEntity<String> {
        val optionalQuestion = questionRepository.findById(questionId)

        if (optionalQuestion.isEmpty) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Questão não encontrada")
        }

        val question = optionalQuestion.get()

        if (answer == question.correctIndex) {
            return ResponseEntity.ok("Resposta correta!")
        } else {
            return ResponseEntity.ok("Resposta incorreta.")
        }
    }

    @DeleteMapping("/{id}")
    fun deleteQuestion(@PathVariable id: UUID): ResponseEntity<String> {
        val optionalQuestion = questionRepository.findById(id)

        if (optionalQuestion.isPresent) {
            questionRepository.deleteById(id)
            return ResponseEntity.status(HttpStatus.OK).body("Questão deletada com sucesso")
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Questão não encontrada")
        }
    }
}
