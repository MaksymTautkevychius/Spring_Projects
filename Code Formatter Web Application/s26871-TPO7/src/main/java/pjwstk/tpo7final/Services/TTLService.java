package pjwstk.tpo7final.Services;

import org.springframework.stereotype.Service;
import pjwstk.tpo7final.Objects.Format;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class TTLService implements Runnable {
    private Map<Integer, LocalDateTime> Formats = new HashMap<>();
    private Duration defaultTTL=Duration.ofMinutes(5);;
    private SerializationService serializationService;

    public TTLService( SerializationService serializationService) {
        this.serializationService = serializationService;
    }

    public synchronized void AddFormat(Format format) {
        Formats.put(format.getId(), LocalDateTime.now().plus(defaultTTL));
    }

    @Override
    public void run() {
        System.out.println("TTLSERVICE STARTED");

            synchronized (this) {
                Iterator<Map.Entry<Integer, LocalDateTime>> iterator = Formats.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Integer, LocalDateTime> entry = iterator.next();
                    LocalDateTime startTime = entry.getValue();
                    int serviceId = entry.getKey();
                    Duration ttl = Duration.between(startTime, LocalDateTime.now());
                    if (ttl.compareTo(Duration.ZERO) >= 0) {
                        iterator.remove();
                        try {
                            serializationService.DeserializeWithId(Integer.toString(serviceId));
                        } catch (IOException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }


}
