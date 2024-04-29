package pjwstk.tpo7final.Objects;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;

public class Format implements Serializable {
    Integer id;
    String data;
    LocalDateTime ttl;

    public Format(int id, String data, LocalDateTime ttl) {
        this.id = id;
        this.data = data;
        setTtl(ttl);
    }
    public void setTtl(LocalDateTime ttl) {
        this.ttl = ttl;
    }
    public LocalDateTime getTtl()
    {
        return ttl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(getTtl());
    }
}
