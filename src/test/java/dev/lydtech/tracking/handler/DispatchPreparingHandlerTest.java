package dev.lydtech.tracking.handler;

import dev.lydtech.dispatch.message.DispatchCompleted;
import dev.lydtech.dispatch.message.DispatchPreparing;
import dev.lydtech.tracking.service.TrackingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.mockito.Mockito.*;

class DispatchPreparingHandlerTest {

    private DispatchPreparingHandler dispatchPreparingHandler;
    private TrackingService trackingService;
    private DispatchPreparing testEvent = DispatchPreparing.builder().orderId(UUID.randomUUID()).build();
    private DispatchCompleted testEventCompleted =
            DispatchCompleted.builder().orderId(UUID.randomUUID()).date(Instant.now().toString()).build();

    @BeforeEach
    void setUp() {
        trackingService = mock(TrackingService.class);
        dispatchPreparingHandler = new DispatchPreparingHandler(trackingService);
    }

    @Test
    void listenPrepare() throws Exception{
        dispatchPreparingHandler.listenPrepare(testEvent);
        verify(trackingService, times(1)).process(testEvent);
    }
    @Test
    void listenCompleted() throws Exception{
        dispatchPreparingHandler.listenCompleted(testEventCompleted);
        verify(trackingService, times(1)).process(testEvent);
    }

    @Test
    void listenPrepare_Throws() throws Exception{
        doThrow(new RuntimeException("Service failure")).when(trackingService).process(testEvent);

        dispatchPreparingHandler.listenPrepare(testEvent);
        verify(trackingService, times(1)).process(testEvent);
    }
    @Test
    void listenCompleted_Throws() throws Exception{
        doThrow(new RuntimeException("Service failure")).when(trackingService).process(testEventCompleted);

        dispatchPreparingHandler.listenCompleted(testEventCompleted);
        verify(trackingService, times(1)).process(testEvent);
    }
}
