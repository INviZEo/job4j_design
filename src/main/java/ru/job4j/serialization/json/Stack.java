package ru.job4j.serialization.json;

import org.json.JSONPropertyIgnore;

public class Stack {
    private OverflowError overflowError;

    @JSONPropertyIgnore
    public OverflowError getOverflowError() {
        return overflowError;
    }

    public void setOverflowError(OverflowError overflowError) {
        this.overflowError = overflowError;
    }

    public String getHappy() {
        return "happy";
    }
}
