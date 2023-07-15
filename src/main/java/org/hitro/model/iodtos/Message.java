package org.hitro.model.iodtos;

import lombok.Getter;
import org.hitro.model.metadatas.MessageMetadata;
import org.hitro.model.metadatas.interfaces.MessageMetadataIf;
import org.hitro.publicinterfaces.HymOutput;
import org.hitro.services.HashService;

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
    private Message(T messageData, MessageMetadataIf messageMetadata){

            this.data = messageData;
            this.messageMetadata = messageMetadata;
    }

    public boolean isValidMessage(){
        return data!=null &&
                messageMetadata.isValidMetadata();
    }

    public static <T> Message getInstance(T messageData, String publisherId, Set<String> tags,String channelId){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        MessageMetadataIf messageMetadata = new MessageMetadata(publisherId,tags,channelId,HashService.getInstance().getHashVal(publisherId+channelId+messageData+timeStamp));
        return new Message(messageData,messageMetadata);
    }
}
