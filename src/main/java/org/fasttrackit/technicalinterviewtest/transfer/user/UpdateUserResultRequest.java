package org.fasttrackit.technicalinterviewtest.transfer.user;

public class UpdateUserResultRequest {

    public int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "UpdateUserResultRequest{" +
                "result=" + result +
                '}';
    }
}
