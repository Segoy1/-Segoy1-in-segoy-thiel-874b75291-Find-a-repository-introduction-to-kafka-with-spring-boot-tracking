package dev.lydtech.tracking.handler;

import dev.lydtech.dispatch.message.DispatchPreparing;
import dev.lydtech.tracking.service.TrackingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

class DispatchPreparingHandlerTest {

    private DispatchPreparingHandler dispatchPreparingHandler;
    private TrackingService trackingService;
    private DispatchPreparing testEvent = DispatchPreparing.builder().orderId(UUID.randomUUID()).build();

    @BeforeEach
    void setUp() {
        trackingService = mock(TrackingService.class);
        dispatchPreparingHandler = new DispatchPreparingHandler(trackingService);
    }

    @Test
    void listen() throws Exception{
        dispatchPreparingHandler.listen(testEvent);
        verify(trackingService, times(1)).process(testEvent);
    }
    @Test
    void listen_Throws() throws Exception{
        doThrow(new RuntimeException("Service failure")).when(trackingService).process(testEvent);

        dispatchPreparingHandler.listen(testEvent);
        verify(trackingService, times(1)).process(testEvent);
    }
}
