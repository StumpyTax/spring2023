package com.example.spring2023.app.services;

import com.example.spring2023.app.entity.ChatEntity;
import com.example.spring2023.app.entity.UserEntity;
import com.example.spring2023.app.repositories.ChatRepository;
import com.example.spring2023.app.repositories.UserRepository;
import com.example.spring2023.domain.Chat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;


    /** Получение чата по ID
     * @param chatId Id чата
     * @return Чат
     * @throws JsonProcessingException
     */
    public  ChatEntity get(Long chatId) throws JsonProcessingException, RuntimeException {
        Optional<ChatEntity> chat=chatRepository.findById(chatId);
        if(chat.isEmpty())
            throw  new RuntimeException("No chat with id: "+chatId);
        return  chat.get();
    }
    /** Создает чат
     * @param name Имя чата
     * @param ownerId Создатель чата
     * @param usersIds ID пользователей
     * @return Чат
     */
    public ChatEntity create(String name, Long ownerId, List<Long> usersIds) throws RuntimeException{
        List<UserEntity> users= StreamSupport.stream(userRepository.findAllById(usersIds).spliterator(), false)
                .toList();
        Optional<UserEntity> owner=userRepository.findById(ownerId);

        if(owner.isEmpty())
            throw  new RuntimeException("No such user with ownerId: "+ownerId);

        ChatEntity newChat=new ChatEntity(name,users,owner.get());
        chatRepository.save(newChat);
        if(users.size()<usersIds.size())
            throw  new RuntimeException("Part of ids not found:"+users.size()+" of "+usersIds.size());
        return  newChat;
    }


    /**Добавляет пользователей в чат
     * @param chatId Id чата
     * @param usersIds ID пользователей
     * @return Измененный чат
     */
    public ChatEntity addUsers(Long chatId,List<Long> usersIds)throws RuntimeException{
        Optional<ChatEntity> chatEntity=chatRepository.findById(chatId);
        if(chatEntity.isEmpty())
            throw  new RuntimeException("No such chat with chatId: "+chatId);
        List<UserEntity> users=StreamSupport.stream(userRepository.findAllById(usersIds).spliterator(),false)
                .toList();
        Chat chat=chatEntity.get().toChat();
        chat.addUsers(users.stream().map(UserEntity::toUser).toList());
        ChatEntity updChat=new ChatEntity(chat);
        chatRepository.save(updChat);
        return  updChat;
    }

    /**Удаляет пользователя из чата
     * @param chatId Id чата
     * @param userId Id Пользователя
     * @return  Измененный чат
     */
    public  ChatEntity deleteUser(Long chatId, Long userId) throws RuntimeException{
        Optional<ChatEntity> chatEntity=chatRepository.findById(chatId);
        if(chatEntity.isEmpty())
            throw  new RuntimeException("No such chat with id: "+chatId);
        Optional<UserEntity> user=userRepository.findById(userId);
        if(user.isEmpty())
            throw  new RuntimeException("No such user with id: "+userId);
        Chat chat=chatEntity.get().toChat();
        if(!chat.inUsers(user.get().toUser()))
            throw  new RuntimeException("No such user with id: "+userId+" in this chat");
        chat.deleteUser(userId);
        ChatEntity updChat=new ChatEntity(chat);
        chatRepository.save(updChat);
        return  updChat;
    }

    /**Меняет имя чата
     * @param chatId Id чата
     * @param newName Новое имя чата
     * @return Старое и новое имя чата
     */
    public  String changeName(Long chatId,String newName) throws RuntimeException{
        Optional<ChatEntity> chatEntity=chatRepository.findById(chatId);
        if(chatEntity.isEmpty())
            throw  new RuntimeException("No chat with id: "+chatId);
        Chat chat=chatEntity.get().toChat();
        chat.setName(newName);
        chatRepository.save(new ChatEntity(chat));
        return "OldName:"+chatEntity.get().getName()+"\nNewName: "+chat.getName();
    }
}
