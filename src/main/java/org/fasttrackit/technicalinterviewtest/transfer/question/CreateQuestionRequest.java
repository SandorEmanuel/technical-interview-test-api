package org.fasttrackit.technicalinterviewtest.transfer.question;

import javax.validation.constraints.NotBlank;

public class CreateQuestionRequest {

    @NotBlank
    private String contentTitle;
    @NotBlank
    private String choice1;
    @NotBlank
    private String choice2;
    @NotBlank
    private String choice3;
    private int correctAnswer;
    private int givenAnswer;

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(int givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    @Override
    public String toString() {
        return "CreateQuestionRequest{" +
                "content='" + contentTitle + '\'' +
                ", choice1='" + choice1 + '\'' +
                ", choice2='" + choice2 + '\'' +
                ", choice3='" + choice3 + '\'' +
                ", correctAnswer=" + correctAnswer +
                ", givenAnswer=" + givenAnswer +
                '}';
    }
}

