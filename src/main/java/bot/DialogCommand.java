package bot;

public interface DialogCommand extends Command {
    Dialog createDialog();

    @Override
    default String execute(String[] args) {
        return createDialog().start();
    }
}