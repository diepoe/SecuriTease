package com.diepoe.securitease;

class Rule {
    private CheckingFunction checkingFunction;
    private String[] feedbackMessages;
    private int threshold;

    public Rule(CheckingFunction checkingFunction, String[] feedbackMessages, int threshold) {
        this.checkingFunction = checkingFunction;
        this.feedbackMessages = feedbackMessages;
        this.threshold = threshold;
    }

    public CheckingFunction getCheckingFunction() {
        return checkingFunction;
    }

    public String[] getFeedbackMessages() {
        return feedbackMessages;
    }

    public String getFeedbackMessage(int index) {
        return feedbackMessages[index];
    }

    public String getRandomFeedbackMessage() {
        int randomIndex = (int) (Math.random() * feedbackMessages.length);
        return feedbackMessages[randomIndex];
    }

    public int getThreshold() {
        return threshold;
    }
}
