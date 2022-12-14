package pl.com.seremak.simplebills.commons.utils;


import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Optional;

public class DateUtils {

    public static final ZoneId ZONE_UTC = ZoneId.of("UTC");

    public static Optional<Instant> toInstantUTC(final LocalDate localDate) {
        return Optional.ofNullable(localDate)
                .map(presentLocalDate -> presentLocalDate.atStartOfDay(ZONE_UTC).toInstant());
    }

    public static Optional<LocalDate> toLocalDate(final Instant instant) {
        return Optional.ofNullable(instant)
                .map(presentInstant -> LocalDate.ofInstant(presentInstant, ZONE_UTC));
    }

    public static Optional<YearMonth> toYearMonth(final Instant instant) {
        return Optional.ofNullable(instant)
                .flatMap(DateUtils::toLocalDate)
                .map(localDate -> YearMonth.of(localDate.getYear(), localDate.getMonth()));
    }

    public static Optional<String> toYearMonthString(final Instant instant) {
        return toYearMonth(instant)
                .map(YearMonth::toString);
    }
}
