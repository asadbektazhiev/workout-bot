package bot;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/** Хранилище всех команд. Нужно, чтобы /help сам видел новые команды. */
public class CommandRegistry {
    private final Map<String, Command> commands = new LinkedHashMap<>();

    public void register(Command command) {
        commands.put(command.name(), command);
    }

    public Optional<Command> find(String name) {
        return Optional.ofNullable(commands.get(name));
    }

    public Collection<Command> all() {
        return Collections.unmodifiableCollection(commands.values());
    }
}