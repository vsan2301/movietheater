package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalDateProviderTests {
    @Test
    void makeSureCurrentTime() {
        System.out.println("current time is - " + LocalDateProvider.singleton().currentDate());
        LocalDateProvider localDateProvider = LocalDateProvider.singleton();
        LocalDateProvider localDateProviderSecond = LocalDateProvider.singleton();
        assertEquals(localDateProvider, localDateProviderSecond);
        assertEquals(localDateProvider.equals(localDateProviderSecond),true);

    }
}
