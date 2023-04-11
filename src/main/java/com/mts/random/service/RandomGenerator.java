package com.mts.random.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mts.random.events.DetailEntity;
import com.mts.random.events.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class RandomGenerator {
    private static final Logger logger = LoggerFactory.getLogger(RandomGenerator.class);

    @Value("${random.apis.array}")
    private String[] random_apis;

    @Autowired
    private KafkaProducer kafkaProducer;

    public String publishMessage() throws IOException {
        String response = getRandomApi();

        DetailEntity detailEntity = new DetailEntity();
        detailEntity.setJsonResult(response);

        DomainEvent domainEvent = new DomainEvent();
        domainEvent.setDetailType(response);
        domainEvent.setDetailEntity(detailEntity);
        String messageKey = domainEvent.getId().toString();

        this.kafkaProducer.sendMessage(messageKey, domainEvent);

        return "Published 1 message, key=" + messageKey +", value=" + domainEvent.toString();
    }

    public String getRandomApi()
    {
        List<String> randomApis = Arrays.asList(random_apis);

        // get a random api
        Random random = new Random();
        int index = random.nextInt(randomApis.size());
        String randomApi = randomApis.get(index);

        logger.info("Random API=" + randomApi);

        return randomApi;
    }
}
