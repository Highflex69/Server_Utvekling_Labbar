package Hibernate;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Teddy on 2016-11-24.
 */
public class DTO_Messages implements Serializable {
    ArrayList<DTO_Message> messagesList;

    public DTO_Messages(){messagesList = new ArrayList<DTO_Message>();}

    public void add(DTO_Message message)
    {
        messagesList.add(message);
    }

    public ArrayList<DTO_Message> getMessagesList() {
        return messagesList;
    }
}
