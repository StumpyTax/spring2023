package com.example.spring2023.app.services;

import com.example.spring2023.app.entity.ChatEntity;
import com.example.spring2023.app.entity.MessageEntity;
import com.example.spring2023.app.entity.UserEntity;
import com.example.spring2023.app.repositories.ChatRepository;
import com.example.spring2023.app.repositories.MessageRepository;
import com.example.spring2023.app.repositories.UserRepository;
import com.example.spring2023.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatRepository chatRepository;
    //get

    /**
     * Создает сообщение
     *
     * @param senderId    Отправитель сообщения
     * @param chatId      Место назначения сообщения
     * @param messageText Текст сообщения
     * @param date        Дата отправки
     * @return Возвращает BaseResponse
     */
    public Message create(Long senderId, Long chatId, String messageText, LocalDateTime date) throws RuntimeException {
        Optional<UserEntity> sender = userRepository.findById(senderId);
        Optional<ChatEntity> chat = chatRepository.findById(senderId);
        if (sender.isEmpty())
            throw new RuntimeException("No sender with id: " + senderId);
        if (chat.isEmpty())
            throw new RuntimeException("No chat with id: " + senderId);
        Message message = new Message(messageText, date, sender.get().toUser(), chat.get().toChat());
        messageRepository.save(new MessageEntity(message));
        return message;
    }

    /**
     * Может ли пользователь изменять сообщение
     *
     * @param messageId id сообщения
     * @param userId    id пользователя
     * @return Возвращает BaseResponse
     */
    public Boolean canChange(Long messageId, Long userId) throws RuntimeException {
        Optional<MessageEntity> message = messageRepository.findById(messageId);
        Optional<UserEntity> user = userRepository.findById(userId);
        if (message.isEmpty())
            throw new RuntimeException("No such message with id: " + messageId);
        if (user.isEmpty())
            throw new RuntimeException("No such user with id: " + userId);

        return message.get().getSender().getId() == user.get().getId();
    }

    /**
     * Проверяет может ли пользователь изменять сообщение
     *
     * @param message Сообщение
     * @param user    Пользователь
     * @return
     */
    private boolean canChange(MessageEntity message, UserEntity user) {
        return message.getSender().getId() == user.getId();
    }

    /**
     * Удаляет сообщение
     *
     * @param messageId Id Сообщения
     * @param userId    Id того кто пытается удалить сообщение
     * @return Возвращает BaseResponse
     */
    public void delete(Long messageId, Long userId) throws RuntimeException {
        Optional<MessageEntity> message = messageRepository.findById(messageId);
        Optional<UserEntity> user = userRepository.findById(userId);
        if (message.isEmpty())
            throw new RuntimeException("No such message with id: " + messageId);
        if (user.isEmpty())
            throw new RuntimeException("No such user with id: " + userId);
        if (!canChange(message.get(), user.get()))
            throw new RuntimeException("This user cant delete message. UserId: " + userId + " MessageId: " + messageId);
        messageRepository.delete(message.get());
    }

    /**
     * Изменение сообщения.
     *
     * @param messageId id Сообщения
     * @param userId    id Пользователя пытающигося изменить сообшение.
     * @return Возвращает BaseResponse
     */
    public void change(Long messageId, Long userId, String newText,LocalDateTime date) throws RuntimeException {
        Optional<MessageEntity> messageEntity = messageRepository.findById(messageId);
        Optional<UserEntity> user = userRepository.findById(userId);
        if (messageEntity.isEmpty())
            throw new RuntimeException("No such message with id: " + messageId);
        if (user.isEmpty())
            throw new RuntimeException("No such user with id: " + userId);
        Message message= messageEntity.get().toMessage();
        message.setText(newText);
        message.setLastChange(date);
        messageRepository.save(new MessageEntity(message));
    }

}
