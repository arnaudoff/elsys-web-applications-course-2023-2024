package com.dreamix.overachievers.util;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;

public class DateUtil {
    private DateUtil() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("DateUtil is an util class and cannot be instantiated!");
    }
    public static LocalDateTime getStartOfTheDay() {
        return LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
    }

    public static LocalDateTime getEndOfTheDay() {
        return LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
    }
}
