package com.mts.random.events;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

// DomainEvent is based on DDD's Domain Event and
//  AWS EventBridge event pattern: https://docs.aws.amazon.com/eventbridge/latest/userguide/eb-events.html
@Data
public class DomainEvent {
    private final String version;
    private final UUID id;
    private String detailType;
    private final String source;
    private final Date date;
    private boolean isError;
    private String message;

    // each Event will have specific Domain's data, in this case it's json result from outbound city api
    private DetailEntity detailEntity;

    public DomainEvent() {
        this.version = "1.0";
        this.id = UUID.randomUUID();
        this.detailType = "city1"; //todo
        this.source = "eda-kafka-random-api";
        this.date = new Date();
        this.isError = false;
        this.message = "successfull";
    }
}
