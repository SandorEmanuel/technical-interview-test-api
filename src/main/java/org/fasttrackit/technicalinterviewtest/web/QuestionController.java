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
    public ResponseEntity updateQuestionGivenAnswer(long id, @RequestBody  UpdateQuestionGivenAnswerRequest request) throws ResourceNotFoundException {
        questionService.updateQuestionGivenAnswer(id,request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
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
