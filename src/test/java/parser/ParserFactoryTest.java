package parser;

import api.AdParseException;
import api.factories.ParserFactory;
import junit.framework.TestCase;
import parser.dateconverter.Factory;


public class ParserFactoryTest extends TestCase {



    /**
     * Проверка на создание нужного класса относительно строки
     * @throws AdParseException
     */
    public void testCreate() throws Exception {
        assertEquals(Avito.class, ParserFactory.create("http://www.avito.ru/ghgh/ghg").getClass());
        assertEquals(Avito.class, ParserFactory.create("https://www.avito.ru/ghgh/ghg").getClass());
        assertEquals(Avito.class, ParserFactory.create("http://avito.ru/ghgh/ghg").getClass());
        assertEquals(Avito.class, ParserFactory.create("https://avito.ru/ghgh/ghg").getClass());

    }


    public void testDrom() throws AdParseException {
        assertEquals(Drom.class, ParserFactory.create("http://drom.ru/ghgh/ghg").getClass());
        assertEquals(Drom.class, ParserFactory.create("http://www.drom.ru/ghgh/ghg").getClass());
        assertEquals(Drom.class, ParserFactory.create("http://www.auto.drom.ru/ghgh/ghg").getClass());
        assertEquals(Drom.class, ParserFactory.create("www.auto.drom.ru/ghgh/ghg").getClass());
        assertEquals(Drom.class, ParserFactory.create("www.auto.drom.ru/ghgh/ghg?dgdfg=ddf+dsf&sdfsdf.d%%").getClass());
        assertEquals(Drom.class, ParserFactory.create("https://www.auto.drom.ru/ghgh/ghg?dgdfg=ddf+dsf&sdfsdf.d%%").getClass());

    }



}
