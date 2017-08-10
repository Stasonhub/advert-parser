package clientcli;

import api.*;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class CliApplication implements Runnable{

    private static Logger logger= LoggerFactory.getLogger(CliApplication.class);

    /**
     * Список консольных опций
     */
    private AdsParserFacade adsParserFacade;
    private CommandLine cmd;
    private CliParser cliParser;


    CliApplication(AdsParserFacade adsParserFacade, String[] args) throws ParseException {
        this.cliParser = new CliParser(args);
        this.cmd = cliParser.parse();
        this.adsParserFacade = adsParserFacade;
    }

    @Override
    public void run() {

         if(cmd.hasOption("h")) {
             cliParser.printHelp();
         }else if(cmd.hasOption("r")){
             runParsing();
         }else if(cmd.hasOption("a")){
             tryAddProject();
         }else if(cmd.hasOption("l")){
             tryListProjects();
         }else if(cmd.hasOption("d")){
             tryDeleteProject();
         }
    }


    /**
     * Запускает парсинг всех проектов
     */
    private void runParsing(){
        try {
            adsParserFacade.runParsing();
        }catch (AdParseException e){
            logger.warn("Parsing error. ",e);
        }
    }

    private void tryListProjects() {
        try{
            listProjects();
        }catch (AdParseException e){
            logger.warn("List project error. ",e);
        }
    }

    private void tryAddProject() {
        try{
            boolean isCreate = addProject();
            String info = isCreate ? "Проект успешно создан" : "Такой проект уже существует";
            logger.info(info);
        }catch (AdParseException e){
            logger.warn("Add project error. ",e);
        }

    }

    private void tryDeleteProject(){
        try {
            deleteProject();
            logger.info("Проект успешно удалён");
        } catch (Exception e) {
            logger.warn("Delete error. ",e);
        }
    }

    private boolean addProject() throws AdParseException {
        String[] arguments = cmd.getOptionValues("a");
        String url = arguments[0].trim();
        String name = arguments[1].trim();
        Project project = new Project(0, name, url, null);
        return adsParserFacade.createProject(project);
    }

    private void deleteProject() throws AdParseException {
        String[] arguments = cmd.getOptionValues("d");
        int id = Integer.parseInt(arguments[0]);
        adsParserFacade.deleteProject(id);
    }

    /**
     * Вывлд проектов на экран
     * @throws AdParseException
     */
    private void listProjects() throws AdParseException {
        adsParserFacade.listProjects()
                .forEach(proj -> logger.info(proj.toString()));
    }
}
