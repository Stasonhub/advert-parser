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
        add.setArgs(2);
        add.setArgName("url> <name");
        options.addOption(add);

        Option deleteProj = new Option("d", "deleteProjectWithAdverts", true, "Удалить проект");
        deleteProj.setArgs(1);
        deleteProj.setArgName("id");
        options.addOption(deleteProj);

        Option list = new Option("l", "listProjects", false, "Список проектов");
        options.addOption(list);

        Option run = new Option("r", "run", false, "Запуск парсинга");
        options.addOption(run);

        Option settings = new Option("s", "settings", true, "Настройки");
        options.addOption(settings);

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
