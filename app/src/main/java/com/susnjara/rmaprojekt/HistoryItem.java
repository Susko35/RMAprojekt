package com.susnjara.rmaprojekt;

public class HistoryItem {
    private String title;
    private String definition;

    public HistoryItem(String title, String definition) {
        this.title = title;
        this.definition = definition;
    }

    public String getTitle() {
        return title;
    }

    public String getDefinition() {
        return definition;
    }
}
