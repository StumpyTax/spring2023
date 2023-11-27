package com.spring2023.stax.app.entity;


import com.spring2023.stax.domain.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "messages")
public class MessageEntity implements IMessage {
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

    public MessageEntity(String text, LocalDateTime date,
                         IUser sender, IChat receiver)throws RuntimeException {
        /*this.id=*/
        setText(text);
        this.date = date;
        this.lastChange = date;
        this.sender = (UserEntity) sender;
        this.receiver = (ChatEntity) receiver;
    }
    public MessageEntity(Long id, String text,
                         LocalDateTime date, LocalDateTime lastChange,
                         IUser sender, IChat receiver)
            throws RuntimeException {
        this.id=id;
        setText(text);
        this.date = date;
        setLastChange(lastChange);
        this.sender = (UserEntity) sender;
        this.receiver = (ChatEntity) receiver;
    }

    public void setText(String newText) throws RuntimeException{
        if (!newText.replaceAll(" ","").isEmpty())
            this.text = newText.strip();
        else
            throw new RuntimeException("Incorrect text");
    }

    public void setLastChange(LocalDateTime date) throws RuntimeException{
        if(date.isAfter(lastChange))
            this.lastChange=date;
        else
            throw new RuntimeException("Incorrect date");
    }
}
