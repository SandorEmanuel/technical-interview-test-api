package org.fasttrackit.technicalinterviewtest.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.technicalinterviewtest.domanin.Question;
import org.fasttrackit.technicalinterviewtest.exception.ResourceNotFoundException;
import org.fasttrackit.technicalinterviewtest.persistance.QuestionRepository;
import org.fasttrackit.technicalinterviewtest.transfer.question.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    public static final Logger LOGGER = LoggerFactory.getLogger(QuestionRepository.class);

    private final QuestionRepository questionRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, ObjectMapper objectMapper) {
        this.questionRepository = questionRepository;
        this.objectMapper = objectMapper;
    }

    public Question createQuestion(CreateQuestionRequest request) {
        LOGGER.info("Creating question {}", request);
        Question question = objectMapper.convertValue(request, Question.class);
        return questionRepository.save(question);
    }

    public Question getQuestion(long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving question {}", id);
        return questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question "+ id +" not found"));
    }

    public Page<QuestionResponse> getQuestions(GetQuestionsRequest request, Pageable pageable) {

        Page<Question> questions;

        LOGGER.info("Retrieving questions >> {}", request);

        questions = questionRepository.findAll(pageable);


    List<QuestionResponse> questionResponses = new ArrayList<>();

        for (Question question : questions.getContent()) {
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setId(question.getId());
        questionResponse.setContentTitle(question.getContentTitle());
        questionResponse.setChoice1(question.getChoice1());
        questionResponse.setChoice2(question.getChoice2());
        questionResponse.setChoice3(question.getChoice3());
        questionResponse.setCorrectAnswer(question.getCorrectAnswer());

        questionResponses.add(questionResponse);
    }
        return new PageImpl<>(questionResponses, pageable, questions.getTotalElements());
}


    public Question updateQuestion(long id, UpdateQuestionRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updating question {}, {}", id, request);
        Question question = getQuestion(id);

        BeanUtils.copyProperties(request, question);

        return questionRepository.save(question);
    }

    public Question updateQuestionGivenAnswer(long id, UpdateQuestionGivenAnswerRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updating question given answer {}, {}", id, request);
        Question question = getQuestion(id);

        BeanUtils.copyProperties(request, question);
        return questionRepository.save(question);
    }

    public void deleteQuestion (long id){
        LOGGER.info("Deleting question {}", id);
        questionRepository.deleteById(id);
        LOGGER.info("Deleted question {}", id);
    }
}
