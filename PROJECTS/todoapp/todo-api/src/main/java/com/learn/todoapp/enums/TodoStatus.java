package com.learn.todoapp.enums;

public enum TodoStatus {
    PENDING("Pending"),
    IN_PROGRESS("In Progress"),
    DONE("Done");

    private final String displayName;

    TodoStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
