package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.model.metadatas.MessageMetadata;
import org.example.model.metadatas.interfaces.MessageMetadataIf;
import org.example.services.HashService;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Set;

@Getter
//Try to make the fields of the Message metadata final
public class Message<T> {
    private T data;
    private MessageMetadataIf messageMetadata;

    public Message(T messageData, String publisherId, Set<String> tags,String channelId){
        try{
            HashService hashService = new HashService();
            this.data = messageData;
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            messageMetadata = new MessageMetadata(publisherId,tags,channelId,hashService.getHashVal(publisherId+channelId+messageData+timeStamp));
        }
        catch (NoSuchAlgorithmException e){
            //log error statements
        }
    }

    public boolean isValidMessage(){
        return data!=null &&
                messageMetadata.isValidMetadata();
    }

}
