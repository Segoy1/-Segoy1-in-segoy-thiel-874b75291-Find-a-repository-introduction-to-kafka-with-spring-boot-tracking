package dev.lydtech.tracking.handler;


import dev.lydtech.dispatch.message.DispatchPreparing;
import dev.lydtech.tracking.service.TrackingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DispatchPreparingHandler {

    private final TrackingService trackingService;

    @KafkaListener(id = "trackingConsumerClient",
            topics = "dispatch.tracking",
            groupId = "tracking.dispatch.status.consumer",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(DispatchPreparing payload) {
        log.info("Message Received: payload: " + payload);
        try {
            trackingService.process(payload);
        } catch (Exception e) {
            log.error("Error");
        }
    }
}
