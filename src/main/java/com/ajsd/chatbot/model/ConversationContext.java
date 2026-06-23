package com.ajsd.chatbot.model;

public class ConversationContext {

    private String currentStep = "ASK_INTENT";
    private String selectedCountry;

    public String getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(String currentStep) {
        this.currentStep = currentStep;
    }

    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public void clear() {
        this.currentStep = "ASK_INTENT";
        this.selectedCountry = null;
    }

}
