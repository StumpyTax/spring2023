package com.spring2023.stax.app.entity;


import com.spring2023.stax.domain.Message;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "messages")
public class MessageEntity {
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;
    @Column(name = "text", nullable = false)
    private String text;
    @ManyToOne
    @JoinColumn(name = "sender")
    private UserEntity sender;
    @ManyToOne
    @JoinColumn(name = "chat")
    private ChatEntity receiver;
    @Column(name = "sending_date")
    private LocalDateTime date;
    @Column(name = "change_date")
    private LocalDateTime lastChange;

    public MessageEntity(Message message) {
        this.id = message.getId();
        this.text = getText();
        this.sender = new UserEntity(message.getSender());
        this.receiver = new ChatEntity(message.getReceiver());
        this.date = message.getDate();
        this.lastChange = message.getLastChange();

    }
    public Message toMessage(){
        return new Message(id,text,date,lastChange,sender.toUser(),receiver.toChat());
    }
}
