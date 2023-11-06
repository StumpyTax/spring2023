package com.example.spring2023.app.entity;


import com.example.spring2023.domain.Message;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "messages")
public class MessageEntity {
    @Id
    @Getter
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;
    @Getter
    @Column(name = "text", nullable = false)
    private String text;
    @Getter
    @ManyToOne
    @JoinColumn(name = "sender")
    private UserEntity sender;
    @Getter
    @ManyToOne
    @JoinColumn(name = "receiver")
    private ChatEntity receiver;
    @Getter
    @Column(name = "sending_date")
    private LocalDateTime date;
    @Getter
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
