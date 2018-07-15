package com.example.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "expiration")
public class TTLExpiration {

    @Id
    private String id;

    @Indexed(expireAfterSeconds = 5)
    private Date createdAt;

    private String value;

    public TTLExpiration() {
    }

    public TTLExpiration(String value) {
        this.createdAt = new Date();
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TTLExpiration{");
        sb.append("id='").append(id).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
