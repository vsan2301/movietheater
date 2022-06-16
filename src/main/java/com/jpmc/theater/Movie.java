package com.jpmc.theater;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.DurationDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.DurationSerializer;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class Movie {
    public static final int FIRST_SHOW = 1;
    public static final int SECOND_SHOW = 2;
    public static final int SEVEN_SHOW = 7;
    private static int MOVIE_CODE_SPECIAL = 1;

    private String title;
    private String description;

    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }


    public double calculateTicketPrice(Showing showing) {
        return ticketPrice - Math.max(getByTime(showing.getStartTime()), getDiscount(showing.getSequenceOfTheDay()));
    }


    private double getDiscount(int showSequence) {
        double specialDiscount = 0;
        if (MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }

        double sequenceDiscount = 0;

        switch (showSequence)
        {
            case FIRST_SHOW:
                sequenceDiscount =3; // $3 discount for 1st show
                break;
            case SECOND_SHOW:
                sequenceDiscount =2; // $2 discount for 2nd show
                break;
            case SEVEN_SHOW:
                sequenceDiscount =1; // $1 discount for 7th show. Note I am assuming 7th sequence not day of the month as problem statement is not clear.
        }


        // biggest discount wins
        return specialDiscount > sequenceDiscount ? specialDiscount : sequenceDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }

    private double getByTime(LocalDateTime startTime) {
        double specialTimeDiscount = 0;
        // If discount is on the basis of month day.
//        if(startTime.getDayOfMonth() == 7)
//            specialDayDiscount = 1;
        if (startTime.getHour() >= 11 && startTime.getHour() < 16)
            specialTimeDiscount = ticketPrice * 0.25;
        return specialTimeDiscount;
    }
}