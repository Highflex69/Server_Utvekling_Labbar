package DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class DTO_Messages implements Serializable{
    private ArrayList<DTO_Message> messagesList;

    public DTO_Messages()
    {
        messagesList = new ArrayList<DTO_Message>();
    }

    public ArrayList<DTO_Message> getMessagesList()
    {
        return messagesList;
    }

    public void add(DTO_Message message)
    {
        messagesList.add(message);
    }
}
