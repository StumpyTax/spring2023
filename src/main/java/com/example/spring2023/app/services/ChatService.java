package com.example.spring2023.app.services;

import com.example.spring2023.app.entity.ChatEntity;
import com.example.spring2023.app.entity.UserEntity;
import com.example.spring2023.app.repositories.ChatRepository;
import com.example.spring2023.app.repositories.UserRepository;
import com.example.spring2023.domain.Chat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
     * @throws RuntimeException
     */
    public  ChatEntity get(Long chatId) throws RuntimeException {
        return  chatRepository.findById(chatId).orElseThrow(()->new RuntimeException("No chat with id: "+chatId));
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
        UserEntity owner=userRepository.findById(ownerId).orElseThrow(()->new RuntimeException("No such user with ownerId: "+ownerId));
        ChatEntity newChat=new ChatEntity(name,users,owner);
        chatRepository.save(newChat);
        if(users.size()<usersIds.size())
            throw  new RuntimeException("Part of ids not found:"+users.size()+" of "+usersIds.size());
        return  newChat;
    }


    /**
     * Добавляет пользователей в чат
     *
     * @param chatId   Id чата
     * @param usersIds ID пользователей
     * @throws RuntimeException
     */
    public void addUsers(Long chatId, List<Long> usersIds)throws RuntimeException{
        ChatEntity chatEntity=chatRepository.findById(chatId).orElseThrow(()->new RuntimeException("No such chat with chatId: "+chatId));
        List<UserEntity> users=StreamSupport.stream(userRepository.findAllById(usersIds).spliterator(),false)
                .toList();
        Chat chat=chatEntity.toChat();
        chat.addUsers(users.stream().map(UserEntity::toUser).toList());
        ChatEntity updChat=new ChatEntity(chat);
        chatRepository.save(updChat);
    }

    /**Удаляет пользователя из чата
     * @param chatId Id чата
     * @param userId Id Пользователя
     * @throws RuntimeException
     *
     */
    public  void deleteUser(Long chatId, Long userId) throws RuntimeException{
        ChatEntity chatEntity=chatRepository.findById(chatId).orElseThrow(()->new RuntimeException("No such chat with id: "+chatId));
        UserEntity user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("No such user with id: "+userId));
        Chat chat=chatEntity.toChat();
        if(!chat.inUsers(user.toUser()))
            throw  new RuntimeException("No such user with id: "+userId+" in this chat");
        chat.deleteUser(userId);
        chatRepository.save(new ChatEntity(chat));
    }

    /**Меняет имя чата
     * @param chatId Id чата
     * @param newName Новое имя чата
     * @return Старое и новое имя чата
     * @throws RuntimeException
     */
    public  String changeName(Long chatId,String newName) throws RuntimeException{
        ChatEntity chatEntity=chatRepository.findById(chatId).orElseThrow(()->new RuntimeException("No chat with id: "+chatId));
        Chat chat=chatEntity.toChat();
        chat.setName(newName);
        chatRepository.save(new ChatEntity(chat));
        return "OldName:"+chatEntity.getName()+"\nNewName: "+chat.getName();
    }
    //delete
}
