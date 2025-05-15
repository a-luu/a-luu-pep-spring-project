package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    MessageRepository messageRepository;    
    @Autowired
    
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }
    
    public Message persistMessage(Message message){
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageFromId(int messageId) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if(optionalMessage.isPresent()){
            return optionalMessage;
        }else{
            return null;
        }
    }

    public Message addMessage(Message message) {
        List<Message> user = this.getAllMessagesFromUser(message.getPostedBy());
        if (message.getMessageText().length() > 255 || message.getMessageText() == "" || user.isEmpty())
            return null;
        return messageRepository.save(message);
    }

    public List<Message> getAllMessagesFromUser(int userId) {
        Optional<List<Message>> optionalMessage =  messageRepository.findMessagesByPostedBy(userId);
        if(optionalMessage.isPresent()){
            return optionalMessage.get();
        }else{
            return null;
        }
    }

    public Message updateMessage(int messageId, Message replacement){
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isPresent() && !replacement.getMessageText().isBlank() && replacement.getMessageText().length() < 256){
            Message message = optionalMessage.get();
            message.setMessageText(replacement.getMessageText());
            return messageRepository.save(message);
        }else{
            return null;
        }
        /*if (messageRepository.getMessageById(messageId) == null || message.getMessageText() == "" || message.getMessageText().length() > 255)
            return null;
        else {
            messageRepository.updateMessage(messageId, message);
            return messageRepository.getMessageById(messageId);
        }*/
    }

    public boolean deleteMessage(int messageId){
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if(optionalMessage.isPresent()){
            messageRepository.deleteById(messageId);
            return true;
        }else{
            return false;
        }
        /*Message message = messageRepository.getMessageById(messageId);
        messageRepository.deleteMessage(messageId);
        if (messageRepository.getMessageById(messageId) == null)
            return message;
        else
            return null;*/
    }
}
