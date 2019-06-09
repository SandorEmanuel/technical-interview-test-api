package org.fasttrackit.technicalinterviewtest;

import org.fasttrackit.technicalinterviewtest.domanin.Question;
import org.fasttrackit.technicalinterviewtest.exception.ResourceNotFoundException;
import org.fasttrackit.technicalinterviewtest.service.QuestionService;
import org.fasttrackit.technicalinterviewtest.transfer.question.CreateQuestionRequest;
import org.fasttrackit.technicalinterviewtest.transfer.question.UpdateQuestionRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionServiceIntegrationTests {

    @Autowired
    private QuestionService questionService;

    @Test
    public void testCreateQuestion_whenValidRequest_thenReturnQuestionWithId() {

        Question question = createQuestion();

        assertThat(question, notNullValue());
        assertThat(question.getId(), greaterThan(0L));
    }

    private Question createQuestion() {
        CreateQuestionRequest request = new CreateQuestionRequest();
        request.setContentTitle("What is MySQL?");
        request.setChoice1("Service");
        request.setChoice2("Class");
        request.setChoice3("Database");
        request.setCorrectAnswer(3);

        return questionService.createQuestion(request);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetQuestion_whenQuestionNotFound_thenThrowResourceNotFoundException() throws ResourceNotFoundException {
        questionService.getQuestion(0);
    }

    @Test
    public void testGetQuestion_whenExistingId_thenReturnMatchingQuestion() throws ResourceNotFoundException {
        Question question = createQuestion();

        Question retrievedQuestion = questionService.getQuestion(question.getId());

        assertThat(retrievedQuestion.getId(), is(question.getId()));
        assertThat(retrievedQuestion.getContentTitle(), is(question.getContentTitle()));
        assertThat(retrievedQuestion.getChoice1(), is(question.getChoice1()));
        assertThat(retrievedQuestion.getChoice2(), is(question.getChoice2()));
        assertThat(retrievedQuestion.getChoice3(), is(question.getChoice3()));
        assertThat(retrievedQuestion.getCorrectAnswer(), is(question.getCorrectAnswer()));

    }

    @Test
    public void testUpdateQuestion_whenValidRequestWithAllFields_thenReturnUpdatedQuestion() throws ResourceNotFoundException {
        Question createdQuestion = createQuestion();

        UpdateQuestionRequest request = new UpdateQuestionRequest();
        request.setContentTitle(createdQuestion.getContentTitle() +"_Edited");
        request.setChoice1(createdQuestion.getChoice1() +"_Edited");
        request.setChoice2(createdQuestion.getChoice2() +"_Edited");
        request.setChoice3(createdQuestion.getChoice3() +"_Edited");
        request.setCorrectAnswer(createdQuestion.getCorrectAnswer() + 10);

        Question updatedQuestion = questionService.updateQuestion(createdQuestion.getId(), request);

        assertThat(updatedQuestion.getContentTitle(), is(request.getContentTitle()));
        assertThat(updatedQuestion.getContentTitle(), not(is(createdQuestion.getContentTitle())));
        assertThat(updatedQuestion.getChoice1(), is(request.getChoice1()));
        assertThat(updatedQuestion.getChoice1(), not(is(createdQuestion.getChoice1())));
        assertThat(updatedQuestion.getChoice2(), is(request.getChoice2()));
        assertThat(updatedQuestion.getChoice2(), not(is(request.getChoice2())));
        assertThat(updatedQuestion.getChoice3(), is(request.getChoice3()));
        assertThat(updatedQuestion.getChoice3(), not(is(request.getChoice3())));
        assertThat(updatedQuestion.getCorrectAnswer(), is(request.getCorrectAnswer()));
        assertThat(updatedQuestion.getCorrectAnswer(), not(is(request.getCorrectAnswer())));
        assertThat(updatedQuestion.getId(), is(createdQuestion.getId()));
        assertThat(updatedQuestion.getId(), not(is(createdQuestion.getId())));

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteQuestion_whenExistingId_thenQuestionIsDeleted() throws ResourceNotFoundException {
        Question createdQuestion = createQuestion();

        questionService.deleteQuestion(createdQuestion.getId());

        questionService.getQuestion(createdQuestion.getId());
    }


}
