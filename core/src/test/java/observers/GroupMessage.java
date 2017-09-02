package observers;

import api.observers.GroupAdvertsToMessage;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;


public class GroupMessage extends TestCase {
    public void testGrouping(){

        GroupAdvertsToMessage group = new GroupAdvertsToMessage(getMessage(401));
        assertEquals(group.getGroupMessages().size(), 2);
        String text = group.getGroupMessages().get(1);
        assertEquals(text.length(), 10);
    }

    private List<String> getMessage(int length){
        List<String> res = new ArrayList<>();
        for(int i=0; i<length; i++){
            res.add("1010101010");
        }
        return res;
    }

    public void testExcept(){

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(1);
        list.add(1);

        list.forEach(this::exceptionThrow);


    }

    private void exceptionThrow(int val)
    {
        if(val == 2){
            throw new RuntimeException("2");

        }else{
            System.out.println("1");
        }
    }
}
