package clientcli;

import org.apache.commons.cli.*;


public class CliParser {

    private Options options;
    private CommandLineParser parser = new DefaultParser();
    private String[] args;

    CliParser(String[] args){
        this.args = args;
    }
    /**
     * Генерирует опции командной строки
     */
    private void generateCliOptions() {
        options = new Options();

        Option add = new Option("a", "add", true, "Добавление проекта");
        add.setArgs(3);
        add.setArgName("url> <name> <settings");
        options.addOption(add);

        Option run = new Option("r", "run", true, "Запуск парсинга");
        run.setArgs(1);
        run.setArgName("settings");
        options.addOption(run);

        Option list = new Option("l", "listProjects", true, "Список проектов");
        list.setArgs(1);
        list.setArgName("settings");
        options.addOption(list);

        Option deleteProj = new Option("d", "deleteProjectWithAdverts", true, "Удалить проект");
        deleteProj.setArgs(2);
        deleteProj.setArgName("id> <settings");
        options.addOption(deleteProj);

        options.addOption("h", "help", false, "Помощь");

    }

    /**
     * Подключает опции и парсит cmd
     *
     * @throws ParseException
     */
    public CommandLine parse() throws ParseException {

        generateCliOptions();
        return parser.parse(options, args);
    }


    public void printHelp(){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "AdsParser", options);
    }
}
