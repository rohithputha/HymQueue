package org.hitro.model.iodtos;

import lombok.Getter;
import org.hitro.model.metadatas.MessageMetadata;
import org.hitro.model.metadatas.interfaces.MessageMetadataIf;
import org.hitro.services.HashService;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Set;

@Getter
//Try to make the fields of the Message metadata final
public class Message<T> {
    private T data;
    private MessageMetadataIf messageMetadata;

    public HymOutput<T,Class> getData(){
        return new HymOutput<>(data, data.getClass());
    }
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
