package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
    @Test
    void specialMovieWith20PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        // This test case will fail as we are providing discount on time also. Modifying it
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(9,20)));
        assertEquals(10, spiderMan.calculateTicketPrice(showing));
    }
    @Test // Testing movie start time between 11Am- 16 PM
    void MovieWith25PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(11,20)));
        assertEquals(9.375, spiderMan.calculateTicketPrice(showing));

    }

    @Test //  movie showing 1st of the day
    void SequenceDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12, 9);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(9,20)));
        assertEquals(9, spiderMan.calculateTicketPrice(showing));

    }

    @Test //  movie showing 12th of the day
    void NoDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12, 9);
        Showing showing = new Showing(spiderMan, 12, LocalDateTime.of(LocalDate.now(), LocalTime.of(9,20)));
        assertEquals(12, spiderMan.calculateTicketPrice(showing));
    }

}
