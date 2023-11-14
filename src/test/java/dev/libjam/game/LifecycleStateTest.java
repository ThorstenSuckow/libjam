package dev.libjam.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public record LifecycleStateTest() {

    @Test
    @DisplayName("LifecycleState")
    void testLifecycleState() {

        assertNotNull(LifecycleState.PREPARING);
        assertNotNull(LifecycleState.SPAWNING);
        assertNotNull(LifecycleState.LIVING);
        assertNotNull(LifecycleState.EXPIRING);
        assertNotNull(LifecycleState.VOID);

    }

}
