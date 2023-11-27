package com.spring2023.stax.app.services;

import com.spring2023.stax.app.entity.ChatEntity;
import com.spring2023.stax.app.entity.MessageEntity;
import com.spring2023.stax.app.entity.UserEntity;
import com.spring2023.stax.app.repositories.ChatRepository;
import com.spring2023.stax.app.repositories.MessageRepository;
import com.spring2023.stax.app.repositories.UserRepository;
import com.spring2023.stax.domain.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatRepository chatRepository;


    /**
     * Получение всех сообщений чата
     *
     * @param chatId id чата
     * @param userId Пользователь, который пытается получить сообщения.
     * @return Список найденных сообщений
     * @throws RuntimeException
     */
    public List<MessageEntity> getAll(Long chatId, Long userId) throws RuntimeException{
        ChatEntity chat=chatRepository.findById(chatId)
                .orElseThrow(()->new RuntimeException("No such chat with id: "+chatId));
        UserEntity user=userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("No user with id: "+userId+" in chat with id: "+chatId ));
        return new ArrayList<>();
    }

    /**
     * Получение сообщения по id
     *
     * @param messageId id сообщения
     * @param userId Пользователь, который пытается получить сообщение.
     * @return Найденное сообщение
     * @throws RuntimeException
     */
    public MessageEntity get(Long messageId, Long userId) throws RuntimeException{
        return null;
    }

    /**
     * Создает сообщение
     *
     * @param senderId    Отправитель сообщения
     * @param chatId      Место назначения сообщения
     * @param messageText Текст сообщения
     * @param date        Дата отправки
     * @return Возвращает BaseResponse
     * @throws RuntimeException
     */
    public IMessage create(Long senderId, Long chatId, String messageText, LocalDateTime date) throws RuntimeException {
        Optional<UserEntity> sender = userRepository.findById(senderId);
        Optional<ChatEntity> chat = chatRepository.findById(senderId);
        if (sender.isEmpty())
            throw new RuntimeException("No sender with id: " + senderId);
        if (chat.isEmpty())
            throw new RuntimeException("No chat with id: " + senderId);
        MessageEntity message = new MessageEntity(messageText, date, sender.get(), chat.get());
        messageRepository.save(message);
        return message;
    }

    /**
     * Может ли пользователь изменять сообщение
     *
     * @param messageId id сообщения
     * @param userId    id пользователя
     * @return Возвращает BaseResponse
     * @throws RuntimeException
     */
    public Boolean canChange(Long messageId, Long userId) throws RuntimeException {
        MessageEntity message = messageRepository.findById(messageId)
                .orElseThrow(()->new RuntimeException("No such message with id: " + messageId));
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("No such user with id: " + userId));

        return message.getSender().getId() == user.getId();
    }

    /**
     * Проверяет может ли пользователь изменять сообщение
     *
     * @param message Сообщение
     * @param user    Пользователь
     * @return
     * @throws RuntimeException
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
     * @throws RuntimeException
     */
    public void delete(Long messageId, Long userId) throws RuntimeException {
        MessageEntity message = messageRepository.findById(messageId)
                .orElseThrow(()->new RuntimeException("No such message with id: " + messageId));
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("No such user with id: " + userId));
        if (!canChange(message, user))
            throw new RuntimeException("This user cant delete message. UserId: " + userId + " MessageId: " + messageId);
        messageRepository.delete(message);
    }

    /**
     * Изменение сообщения.
     *
     * @param messageId id Сообщения
     * @param userId    id Пользователя пытающигося изменить сообшение.
     * @return Возвращает BaseResponse
     * @throws RuntimeException
     */
    public void change(Long messageId, Long userId, String newText,LocalDateTime date) throws RuntimeException {
        MessageEntity message = messageRepository.findById(messageId)
                .orElseThrow(()-> new RuntimeException("No such message with id: " + messageId));
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("No such user with id: " + userId));
        message.setText(newText);
        message.setLastChange(date);
        messageRepository.save(message);
    }

}
