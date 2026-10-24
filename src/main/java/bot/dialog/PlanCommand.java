package bot.dialog;

public class PlanCommand implements DialogCommand {
    @Override
    public String name() {
        return "plan";
    }

    @Override
    public String description() {
        return "Подобрать программу тренировок (диалог из 3 вопросов)";
    }

    @Override
    public String help() {
        return "/plan — бот спросит цель, место занятий и число тренировок в неделю, "
                + "затем составит программу. Отмена в любой момент: /cancel";
    }

    @Override
    public Dialog createDialog() {
        return new PlanDialog();
    }
}