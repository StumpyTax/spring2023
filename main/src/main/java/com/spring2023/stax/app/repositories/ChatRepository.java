package com.spring2023.stax.app.repositories;

import com.spring2023.stax.app.entity.ChatEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends CrudRepository<ChatEntity, Long> {
}
