package org.fasttrackit.technicalinterviewtest.transfer.question;

public class UpdateQuestionGivenAnswerRequest {

    private Long id;
    private Integer givenAnswer;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(Integer givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    @Override
    public String toString() {
        return "UpdateQuestionGivenAnswerRequest{" +
                "id=" + id +
                ", givenAnswer=" + givenAnswer +
                '}';
    }
}

