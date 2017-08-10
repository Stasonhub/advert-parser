package parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


public class Drom extends AbstractParser {

    @Override
    Stream<Element> getAdvertItems(Document site) {
        return site.select(".row").stream();
    }

    @Override
    Map<String, String> parseItem(Element advert){
        Map<String, String> resAdvert = new HashMap<>();
        Elements rowCells = advert.select("td");

        resAdvert.put(HASH_MAP_KEY_TITLE, rowCells.get(2).text());
        resAdvert.put(HASH_MAP_KEY_DESC, rowCells.get(3).text());
        resAdvert.put(HASH_MAP_KEY_SUM, rowCells.get(6).select(".f14").text());
        resAdvert.put(HASH_MAP_KEY_DATE, rowCells.get(0).text());
        resAdvert.put(HASH_MAP_KEY_URL, rowCells.get(6).select("a").attr("href"));
        return resAdvert;
    }

}
