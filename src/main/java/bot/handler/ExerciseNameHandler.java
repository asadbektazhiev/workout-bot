package bot.handler;

public class ExerciseNameHandler implements TextHandler {
    @Override
    public String name() {
        return "exercise";
    }

    @Override
    public String handle(String input) {
        String[] words = input.trim().toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1));
        }
        return sb.toString();
    }
}