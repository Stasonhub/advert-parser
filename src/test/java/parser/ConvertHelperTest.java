package parser;


import junit.framework.TestCase;

public class ConvertHelperTest extends TestCase{

    public void testConvertSum(){

        ConvertHelper converter = new ConvertHelper();

        String sum = "1 985 000 руб.";
        String sum1 = "1 985 000";
        String sum2 = "1 985 000руб.";
        String sum3 = "1985000";
        float fSum =1985000;

        assertEquals(fSum, converter.convertSum(sum) );
        assertEquals(fSum, converter.convertSum(sum1) );
        assertEquals(fSum, converter.convertSum(sum2) );
        assertEquals(fSum, converter.convertSum(sum3) );

    }

}
