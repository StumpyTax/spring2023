package com.tests;

import com.spring2023.stax.app.entity.ChatEntity;
import com.spring2023.stax.app.entity.MessageEntity;
import com.spring2023.stax.app.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class MessagesTests {
    @Test
    public void setEmptyText(){

        RuntimeException thrown= Assertions.assertThrows(RuntimeException.class, ()->{
            MessageEntity message = new MessageEntity("adsf",
                    LocalDateTime.now(),
                    new UserEntity(),
                    new ChatEntity());

            message.setText("");});
        Assertions.assertEquals("Incorrect text", thrown.getMessage());
    }
    @Test
    public void setSpaceText(){
        RuntimeException thrown=Assertions.assertThrows(RuntimeException.class, ()->{
            MessageEntity message = new MessageEntity("adsf",
                    LocalDateTime.now(),
                    new UserEntity(),
                    new ChatEntity());

            message.setText("           ");});
        Assertions.assertEquals("Incorrect text", thrown.getMessage());
    }

    @Test
    public void setUserPassword(){
        MessageEntity message = new MessageEntity("adsf",
                LocalDateTime.now(),
                new UserEntity(),
                new ChatEntity());

        message.setText("ggg");
        Assertions.assertEquals("ggg", message.getText());
    }

    @Test
    public  void setLastChangeBefore(){
        RuntimeException thrown=Assertions.assertThrows(RuntimeException.class, ()->{
            MessageEntity message = new MessageEntity("adsf", LocalDateTime.now(),
                    new UserEntity(), new ChatEntity());
            message.setLastChange(LocalDateTime.now().minusMonths(9));});
        Assertions.assertEquals("Incorrect date", thrown.getMessage());
    }

    @Test
    public  void setLastChange(){
            MessageEntity message = new MessageEntity("adsf", LocalDateTime.now(), new UserEntity(), new ChatEntity());
            LocalDateTime date=LocalDateTime.now().plusMonths(9);
            message.setLastChange(date);
            Assertions.assertEquals(date.toString(), message.getLastChange().toString());
    }

}
