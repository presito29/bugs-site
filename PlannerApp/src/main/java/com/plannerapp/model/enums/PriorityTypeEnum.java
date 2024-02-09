package com.plannerapp.model.enums;

public enum PriorityTypeEnum {

    URGENT("Urgent"),
    IMPORTANT("Important"),
    LOW("Low");

    public final String string;

    PriorityTypeEnum(String s) {
        this.string = s;
    }
}
