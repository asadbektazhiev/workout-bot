package handler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleApp {
    private final Map<String, TextHandler> handlers = new LinkedHashMap<>();
    private TextHandler current;   // активный обработчик

    public void register(TextHandler handler) {
        handlers.put(handler.name(), handler);
        if (current == null) {
            current = handler;
        }
    }

    public String process(String line) {
        String text = line.trim();
        if (text.isEmpty()) {
            return "Введите текст. Режимы: /modes";
        }

        String[] parts = text.split("\\s+");
        String command = parts[0];

        if (command.equals("/modes")) {
            return "Доступные режимы: " + String.join(", ", handlers.keySet())
                    + ". Сейчас: " + current.name();
        }
        if (command.equals("/mode")) {
            if (parts.length < 2) {
                return "Укажите режим: /mode <имя>. Список: /modes";
            }
            TextHandler found = handlers.get(parts[1].toLowerCase());
            if (found == null) {
                return "Нет такого режима. Список: /modes";
            }
            current = found;
            return "Режим: " + found.name();
        }
        return current.handle(text);
    }

    public void run() {
        System.out.println("Введите название упражнения, я приведу его к нужному виду.");
        System.out.println("/modes — список режимов, /mode <имя> — сменить режим, /exit — выход");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String line = scanner.nextLine();
            if (line.trim().equalsIgnoreCase("/exit")) {
                System.out.println("До встречи!");
                break;
            }
            System.out.println(process(line));
        }
    }

    public static void main(String[] args) {
        ConsoleApp app = new ConsoleApp();
        app.register(new ExerciseNameHandler());
        app.register(new UpperCaseHandler());
        app.register(new CamelCaseHandler());
        app.run();
    }
}