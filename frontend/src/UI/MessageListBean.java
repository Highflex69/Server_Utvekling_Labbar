package UI;

import DTO.DTO_Message;

import java.util.ArrayList;

public class MessageListBean {
    private ArrayList<DTO_Message> messageList;

    public MessageListBean() {
        messageList = new ArrayList<>();
    }

    public int noOfMesssages() {
        return messageList.size();
    }

    public DTO_Message getMessage(int index) {
        return messageList.get(index);
    }
}
