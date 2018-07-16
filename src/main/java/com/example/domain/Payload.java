package com.example.domain;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Payload implements Serializable {

    @Id
    private String id;

    private String description;

    private Date createAt;

    private Boolean processed;

    private Integer iteration;

    private Integer maxRetry;

    private Long time;

    private TimeUnit timeUnit;

    private Object value;

    public Payload() {
    }

    public Payload(String description, Date createAt, Boolean processed, Integer iteration, Integer maxRetry, Long time, TimeUnit timeUnit, Object value) {
        this.createAt = createAt == null ? new Date() : createAt;
        this.processed = processed == null ? Boolean.FALSE : processed;
        this.iteration = iteration == null ? Integer.valueOf(0) : iteration;
        this.maxRetry = maxRetry == null ? Integer.valueOf(5) : maxRetry;
        this.time = time == null ? Long.valueOf(1L) : time;
        this.timeUnit = timeUnit == null ? TimeUnit.HOURS : timeUnit;
        this.description = description;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public Integer getIteration() {
        return iteration;
    }

    public void setIteration(Integer iteration) {
        this.iteration = iteration;
    }

    public Integer getMaxRetry() {
        return maxRetry;
    }

    public void setMaxRetry(Integer maxRetry) {
        this.maxRetry = maxRetry;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Payload{");
        sb.append("id='").append(id).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", createAt=").append(createAt);
        sb.append(", processed=").append(processed);
        sb.append(", iteration=").append(iteration);
        sb.append(", maxRetry=").append(maxRetry);
        sb.append(", time=").append(time);
        sb.append(", timeUnit=").append(timeUnit);
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}
