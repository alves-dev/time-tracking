package br.com.igorma.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class TimeTrackingTest {

    @Test
    void crateTimeTrackingTest() {
        ZonedDateTime dateTime = ZonedDateTime.now().minusHours(3);

        TimeTracking time = TimeTracking.builder()
                .type("estudo")
                .activity("programacao")
                .subActivity("quarkus")
                .startTime(dateTime)
                .endTime(ZonedDateTime.now())
                .description("Estudando Quarkus")
                .build();

        assertEquals("ESTUDO", time.getType());
        assertEquals("PROGRAMACAO", time.getActivity());
        assertEquals("QUARKUS", time.getSubActivity());
        assertEquals(dateTime, time.getStartTime());
        assertEquals("Estudando Quarkus", time.getDescription());
    }

    @Test
    void crateTimeTrackingMinimumTest() {
        TimeTracking time = TimeTracking.builder()
                .type("estudo")
                .build();

        assertEquals("ESTUDO", time.getType());
        assertTrue(time.getStartTime().isBefore(ZonedDateTime.now()));
        assertNull(time.getActivity());
        assertNull(time.getSubActivity());
        assertNull(time.getDescription());
        assertNull(time.getEndTime());
    }

    @Test
    void crateTimeTrackingWithStartAfterNowTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                TimeTracking.builder()
                        .type("estudo")
                        .startTime(ZonedDateTime.now().plusHours(3))
                        .build()
        );

        String expectedErrorMessage = "Start time cannot be in the future";
        String actualErrorMessage = exception.getMessage();
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    void crateTimeTrackingWithEndBeforeStartTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                TimeTracking.builder()
                        .type("estudo")
                        .startTime(ZonedDateTime.now().minusHours(2))
                        .endTime(ZonedDateTime.now().minusHours(3))
                        .build()
        );

        String expectedErrorMessage = "End time cannot be before start time";
        String actualErrorMessage = exception.getMessage();
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    void crateTimeTrackingWithEndAfterNowTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                TimeTracking.builder()
                        .type("estudo")
                        .endTime(ZonedDateTime.now().plusHours(3))
                        .build()
        );

        String expectedErrorMessage = "End time cannot be in the future";
        String actualErrorMessage = exception.getMessage();
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }
}
