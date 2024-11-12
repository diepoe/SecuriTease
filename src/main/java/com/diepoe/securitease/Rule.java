package com.diepoe.securitease;

class Rule {
    private CheckingFunction checkingFunction;
    private String[] feedbackMessage;
    private int threshold;

    public Rule(CheckingFunction checkingFunction, String[] feedbackMessage, int threshold) {
        this.checkingFunction = checkingFunction;
        this.feedbackMessage = feedbackMessage;
        this.threshold = threshold;
    }

    public CheckingFunction getCheckingFunction() {
        return checkingFunction;
    }

    public String[] getFeedbackMessage() {
        return feedbackMessage;
    }

    public int getThreshold() {
        return threshold;
    }
}
