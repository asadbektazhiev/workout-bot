package bot;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bot bot = Bot.createDefault();
        System.out.println(bot.greeting());

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
            System.out.println(bot.handle(line));
        }
    }
}