package com.relfor.pcs.auditing.util;

import com.relfor.pcs.auditing.exception.InvalidDateException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

public class ParseLocalDateToInstant {

    public static Instant parseInstant(String timestamp, boolean isStart) throws InvalidDateException {
        if (timestamp == null || timestamp.isEmpty()) {
            throw new InvalidDateException("Timestamp cannot be null or empty.");
        }
        try {
            LocalDate localDate = LocalDate.parse(timestamp);
            if (isStart) {
                return localDate.atStartOfDay(ZoneId.of("UTC")).toInstant();
            } else {
                return localDate.atTime(LocalTime.MAX).atZone(ZoneId.of("UTC")).toInstant();
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Invalid date format: " + timestamp, e);
        }
    }
}
