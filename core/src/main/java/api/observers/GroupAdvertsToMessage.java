package api.observers;


import java.util.ArrayList;
import java.util.List;

/**
 * Группирует множество сообщений из списка в несколько больших сообщений
 */
public class GroupAdvertsToMessage {

    private static final int MESSAGE_MAX_LENGTH = 4000;
    private List<String> messages;
    private List<String> groupMessages;
    private String groupTextMessages = "";


    public GroupAdvertsToMessage(List<String> messages){
        this.messages = messages;
        groupMessages = new ArrayList<>();
    }

    /**
     * Возвращает список сгруппированных сообщений
     * @return
     */
    public List<String> getGroupMessages(){

        messages.forEach(this::groupMessage);
        if(!groupTextMessages.isEmpty()){
            groupMessages.add(groupTextMessages);
        }
        return groupMessages;
    }

    private void groupMessage(String message){

        if( isGroupReady(message)){
            groupMessages.add(groupTextMessages);
            groupTextMessages = message;
        }else{
            groupTextMessages += message;
        }
    }

    private boolean isGroupReady(String nextAdvertMessage){
        int newMessageLength = groupTextMessages.length() + nextAdvertMessage.length();
        return newMessageLength > MESSAGE_MAX_LENGTH;
    }

}
