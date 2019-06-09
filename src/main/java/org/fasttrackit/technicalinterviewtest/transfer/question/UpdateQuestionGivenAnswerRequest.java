package org.fasttrackit.technicalinterviewtest.transfer.question;

public class UpdateQuestionGivenAnswerRequest {

    private long id;
    private int givenAnswer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(int givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

}
