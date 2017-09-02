package api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * класс для работы с ссылками.
 */
public class UrlParser {

    private String url;
    private String domain;
    /**
     * Возвращает домен первого уровня
     * приведённый к виду ( https:/www.avito.ru/cars/ => Avito )
     * @param url - ссылка
     * @return
     */
    public String getFirstLevelDomain(String url){
        this.url =url;
        this.domain = getUrlDomain();
        return firstToUpper(getDomainByLevel(1));
    }


    private String getUrlDomain(){
        Pattern pattern = getPattern();
        Matcher m = pattern.matcher(url);
        String text = "";
        while (m.find()) {
            text = m.group(1);
        }
        return text;
    }

    private Pattern getPattern(){
        Pattern pattern;
        if(url.contains("www.")){
            pattern = Pattern.compile("www.(.*?).ru.*");
        }else{
            pattern = Pattern.compile(".*http.*?[/]([a-z].*?).ru.*");
        }
        return pattern;
    }

    /**
     * Возвращает домен нужного уровня
     * @param level уровень
     * @return
     */
    private String getDomainByLevel(int level){
        String[] lvls = domain.split("\\.");
        int lastIndex = lvls.length-level;
        return lastIndex >= 0? lvls[lastIndex]: domain;
    }

    /**
     * Переводит первый символ в верхний регистр
     * @param str - строка
     * @return
     */
    private String firstToUpper(String str){
        String result = "";
        if(!str.isEmpty()){
            result = str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return result;
    }
}
