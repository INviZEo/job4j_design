package ru.job4j.serialization.json;

import org.json.JSONObject;

public class OverflowError {
    private Stack stack;

    public Stack getStack() {
        return stack;
    }

    public void setStack(Stack stack) {
        this.stack = stack;
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        OverflowError overflowError = new OverflowError();
        stack.setOverflowError(overflowError);
        overflowError.setStack(stack);

        System.out.println(new JSONObject(overflowError));
    }
}
