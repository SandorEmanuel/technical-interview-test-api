package org.fasttrackit.technicalinterviewtest.web;

import org.fasttrackit.technicalinterviewtest.domanin.Question;
import org.fasttrackit.technicalinterviewtest.exception.ResourceNotFoundException;
import org.fasttrackit.technicalinterviewtest.service.QuestionService;
import org.fasttrackit.technicalinterviewtest.transfer.question.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
@CrossOrigin
public class QuestionController {


    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestion (@PathVariable ("id") long id) throws ResourceNotFoundException {
        Question response = questionService.getQuestion(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody @Valid CreateQuestionRequest request){
        Question response = questionService.createQuestion(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateQuestion(@PathVariable("id") long id, @RequestBody @Valid UpdateQuestionRequest request) throws ResourceNotFoundException {
        questionService.updateQuestion(id,request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity updateQuestionGivenAnswer(@RequestBody  UpdateQuestionGivenAnswerRequest request) throws ResourceNotFoundException {
        System.out.println("updateQuestionGivenAnswer:" + request.toString());
        questionService.updateQuestionGivenAnswer(request.getId(),request);

        Question question = questionService.getQuestion(request.getId());
        Boolean success  = (question.getCorrectAnswer() == request.getGivenAnswer());
        String successJSON = "{\"success\": " + success.toString() + " , \"id\": " + request.getId() + ", \"answer\":" + request.getGivenAnswer() + "}";

        return new ResponseEntity<>(successJSON, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteQuestion(@PathVariable("id") long id) {
        questionService.deleteQuestion(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Page<QuestionResponse>> getQuestions(
            @Valid GetQuestionsRequest request, Pageable pageable) {
        Page<QuestionResponse> response = questionService.getQuestions(request, pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
