package com.futbol.api_party.utils;

import com.futbol.api_party.persistence.entity.Match;

import java.time.Duration;
import java.time.LocalDateTime;

public class MatchTimeUtil {

    public static String calculateRelativeMinuteFormatted(Match match, LocalDateTime eventTime) {
        if (match == null || eventTime == null) {
            return null;
        }

        // Event outside the range of the match
        if (eventTime.isBefore(match.getStartFirstTime()) || eventTime.isAfter(match.getEndSecondExtraTime())) {
            return null;
        }

        int totalMinutes = 0;

        // **First time with added time**
        if (!eventTime.isAfter(match.getEndFirstTime())) {
            int minute = (int) Duration.between(match.getStartFirstTime(), eventTime).toMinutes();
            if (minute > 45) {
                return "45+" + (minute - 45);
            }
            return String.valueOf(minute);
        }

        totalMinutes += (int) Duration.between(match.getStartFirstTime(), match.getEndFirstTime()).toMinutes(); // 45 + agregado

        // **If the event occurs during the first half break, it is invalid.**
        if (eventTime.isBefore(match.getStartSecondTime())) {
            return null;
        }

        // **Second time with added time**
        if (!eventTime.isAfter(match.getEndSecondTime())) {
            int minute = totalMinutes + (int) Duration.between(match.getStartSecondTime(), eventTime).toMinutes();
            if (minute > 90) {
                return "90+" + (minute - 90);
            }
            return String.valueOf(minute);
        }

        totalMinutes += (int) Duration.between(match.getStartSecondTime(), match.getEndSecondTime()).toMinutes(); // 90 + overtime

        // **If the event occurs at halftime before the first overtime, it is invalid.**
        if (eventTime.isBefore(match.getStartFirstExtraTime())) {
            return null;
        }

        // **First extra time with added time**
        if (!eventTime.isAfter(match.getEndFirstExtraTime())) {
            int minute = totalMinutes + (int) Duration.between(match.getStartFirstExtraTime(), eventTime).toMinutes();
            if (minute > 105) {
                return "105+" + (minute - 105);
            }
            return String.valueOf(minute);
        }

        totalMinutes += 15; // First full extra time

        // **If the event occurs at half-time before the second overtime, it is invalid.**
        if (eventTime.isBefore(match.getStartSecondExtraTime())) {
            return null;
        }

        // **Second overtime with added time**
        int minute = totalMinutes + (int) Duration.between(match.getStartSecondExtraTime(), eventTime).toMinutes();
        if (minute > 120) {
            return "120+" + (minute - 120);
        }
        return String.valueOf(minute);
    }
}


