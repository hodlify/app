package ru.nikitazhelonkin.coinbalance.data.api.response;


public class BNBBalanceResponse {

    private String mStatus;
    private String mMessage;
    private String mResult;

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getResult() {
        return mResult;
    }

    public void setResult(String result) {
        mResult = result;
    }

}
