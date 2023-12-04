package com.spring2023.stax.app.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring2023.stax.app.entity.ChatEntity;
import com.spring2023.stax.app.entity.UserEntity;
import com.spring2023.stax.app.repositories.ChatRepository;
import com.spring2023.stax.app.repositories.UserRepository;
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

        List<UserEntity> users= StreamSupport.stream(
                userRepository.findAllById(usersIds)
                        .spliterator(), false).toList();

        UserEntity owner=userRepository.findById(ownerId)
                .orElseThrow(()->new RuntimeException("No such user with ownerId: "+ownerId));
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
        ChatEntity chat=chatRepository.findById(chatId)
                .orElseThrow(()->new RuntimeException("No such chat with chatId: "+chatId));
        List<UserEntity> users=StreamSupport.stream(userRepository.findAllById(usersIds).spliterator(),false)
                .toList();
        chat.addUsers(users);
        chatRepository.save(chat);
    }

    /**Удаляет пользователя из чата
     * @param chatId Id чата
     * @param userId Id Пользователя
     * @throws RuntimeException
     *
     */
    public  void deleteUser(Long chatId, Long userId) throws RuntimeException{
        ChatEntity chat=chatRepository.findById(chatId)
                .orElseThrow(()->new RuntimeException("No such chat with id: "+chatId));
        UserEntity user=userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("No such user with id: "+userId));
        if(!chat.inUsers(user))
            throw  new RuntimeException("No such user with id: "+userId+" in this chat");
        if(chat.getOwner().getId()==userId){

        }
        chat.deleteUser(userId);
        chatRepository.save(chat);
    }

    /**
     * Меняет имя чата
     *
     * @param chatId Id чата
     * @param newName Новое имя чата
     * @return Старое и новое имя чата
     * @throws RuntimeException
     */
    public  String changeName(Long chatId,String newName) throws RuntimeException{
        ChatEntity chat=chatRepository.findById(chatId)
                .orElseThrow(()->new RuntimeException("No chat with id: "+chatId));
        chat.setName(newName);
        chatRepository.save(chat);
        return "OldName:"+chat.getName()+"\nNewName: "+chat.getName();
    }
    public void deleteOwner(Long chatId){

    }
    public  void changeOwner(Long chatId,Long newOwnerId){

    }
}
