package com.example.schedule;

import com.example.domain.Payload;
import com.example.repository.PayloadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(Scheduled.class);

    @Autowired
    private PayloadRepository payloadRepository;

    @Scheduled(fixedRate = 60000)
    public void start() {
        LOGGER.info("starting consume payloads.");

        final List<Payload> payloads = payloadRepository.findAll();
//        final List<Payload> payloads = payloadRepository.findAllIterationLessThanMaxRetry();

        payloads.forEach(payload -> {
            LOGGER.info("processing payload={}", payload);

            if (payload.getIteration().compareTo(payload.getMaxRetry()) >= 0) {
                LOGGER.warn("the maximum number of attempts has expired for payload id={}", payload.getId());
                return;
            }

            execute(payload);
        });
    }

    private void execute(Payload payload) {
        try {
            final Object payloadValue = payload.getValue();
            // execute something with payload...

            LOGGER.info("finishing process for payload id={}", payload.getId());

            // updating payload as processed
            payload.setProcessed(Boolean.TRUE);
            payloadRepository.save(payload);
        } catch (Exception e) {
            LOGGER.error("Error while processing payload. Put to try next time", e);
            // updating payload interation
            int iteration = payload.getIteration();
            payload.setIteration(++iteration);
            payloadRepository.save(payload);
        }
    }
}
