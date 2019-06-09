package org.fasttrackit.technicalinterviewtest.transfer.question;

import java.util.Objects;

public class QuestionResponse {

    private long id;
    private String contentTitle;
    private String choice1;
    private String choice2;
    private String choice3;
    private int correctAnswer;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionResponse that = (QuestionResponse) o;
        return id == that.id &&
                correctAnswer == that.correctAnswer &&
                Objects.equals(contentTitle, that.contentTitle) &&
                Objects.equals(choice1, that.choice1) &&
                Objects.equals(choice2, that.choice2) &&
                Objects.equals(choice3, that.choice3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contentTitle, choice1, choice2, choice3, correctAnswer);
    }

    @Override
    public String toString() {
        return "QuestionResponse{" +
                "id=" + id +
                ", content='" + contentTitle + '\'' +
                ", choice1='" + choice1 + '\'' +
                ", choice2='" + choice2 + '\'' +
                ", choice3='" + choice3 + '\'' +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
