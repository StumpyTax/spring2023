package com.example.spring2023.app.repositories;


import com.example.spring2023.app.entity.MessageEntity;
import com.example.spring2023.app.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Long> {
}
