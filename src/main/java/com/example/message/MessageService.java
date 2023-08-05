package com.example.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Utils.ModelUtils.MessageUtils;
import com.example.account.AccountRepository;

@Service
public class MessageService {
    @Autowired(required = true)
    MessageRepository messageDAO;
    @Autowired(required = true)
    AccountRepository accountDAO;

    public Message addMessage(Message message) {
        if (MessageUtils.messageIsValid(message, accountDAO::accountWithIdExists)) {
            return messageDAO.save(message);
        }
        return null;
    }

    public Message addMessage(int posted_by, String message_text, long time_elapsed) {
        return addMessage(new Message(0, posted_by, message_text, time_elapsed));
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message findMessageById(int id) {
        return messageDAO.getMessageByID(id);
    }

    public Message deleteMessageById(int id) {
        Message message = findMessageById(id);
        if (message != null) messageDAO.deleteMessage(id);
        return message;
    }

    public Message updateMessageById(int id, Message msg) {
        if (MessageUtils.messageIsValid(msg)) {
            Message message = findMessageById(id);
            if (message != null) {
                messageDAO.updateMessage(msg.getMessage_text(), id);
                message.setMessage_text(msg.getMessage_text());
                return message;
            }
        }
        return null;
    }

    public List<Message> findAllMessagesByAUser(int account_id) {
        return messageDAO.getMessageByAccount(account_id);
    }
}
