package pjwstk.tpo7final.Services;


import pjwstk.tpo7final.Objects.Format;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class SerializationService  {
    TTLService ttlService = new TTLService(this);
    boolean flag =true;



    public void SerializeFormat(Duration duration,Format ToSerialize) throws IOException {
        if (flag)
        {
            ttlService.run();
            flag=false;
        }
        Duration ttl = duration;
        ToSerialize.setTtl(giveTtl(ttl));
        ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(ToSerialize.getId()+".ser"));
            write.writeObject(ToSerialize);
            ttlService.AddFormat(ToSerialize);
    }
    public Format DeserializeWithId(String id) throws IOException, ClassNotFoundException {
        ObjectInputStream read = new ObjectInputStream(new FileInputStream(id + ".ser"));
        Object form = read.readObject();
        Format format = (Format) form;
        read.close();
        return format;
    }


    public LocalDateTime giveTtl(Duration ttl)
    {
        return  LocalDateTime.now().plusDays(ttl.toDays());
    }
}
