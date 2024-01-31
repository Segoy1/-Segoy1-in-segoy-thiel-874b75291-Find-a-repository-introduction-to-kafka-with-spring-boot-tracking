package dev.lydtech.dispatch.message;

import dev.lydtech.tracking.message.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrackingStatusUpdated {
    UUID orderId;
    Status status;
}
