package com.example.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "domain")
public class Domain implements Serializable {

    @Id
    private String id;

    @Indexed(unique = true)
    private String domain;

    public Domain() {
    }

    public Domain(String domain) {
        this.domain = domain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Domain{");
        sb.append("id=").append(id);
        sb.append(", domain='").append(domain).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
