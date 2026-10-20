package handler;

public class UpperCaseHandler implements TextHandler {
    @Override
    public String name() {
        return "upper";
    }

    @Override
    public String handle(String input) {
        return input.toUpperCase();
    }
}