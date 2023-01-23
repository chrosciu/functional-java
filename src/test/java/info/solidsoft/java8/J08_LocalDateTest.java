package info.solidsoft.java8;

import info.solidsoft.java8.holidays.Holidays;
import info.solidsoft.java8.holidays.PolishHolidays;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.Stream;

import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static org.assertj.core.api.Assertions.assertThat;

public class J08_LocalDateTest {

    private final Holidays holidays = new PolishHolidays();

    /**
     * Hint: Use PolishHolidays to get holidays in Poland
     * Hint: consider iterate(), limit() and filter()
     */
    @Test
    public void shouldCountNumberOfHolidaysIn2014() {
        //given
        final Stream<LocalDate> holidaysIn2014 = Stream.iterate(
                LocalDate.of(2014, JANUARY, 1),
                date -> date.isBefore(LocalDate.of(2015, JANUARY, 1)),
                date -> new PolishHolidays().nextHolidayAfter(date)
        );

        //when
        final long numberOfHolidays = holidaysIn2014.count();

        //then
        assertThat(numberOfHolidays).isEqualTo(113);
    }

    /**
     * @see TemporalAdjusters
     */
    @Test
    public void shouldApplyBuiltIntTemporalAdjuster() {
        //given
        final LocalDate today = LocalDate.of(2014, MAY, 12);

        //when
        final LocalDate previousWednesday = today.with(TemporalAdjusters.previous(DayOfWeek.WEDNESDAY));

        //then
        assertThat(previousWednesday).isEqualTo(LocalDate.of(2014, MAY, 7));
    }

    @Test
    public void shouldApplyCustomTemporalAdjuster() {
        //given
        final LocalDate today = LocalDate.of(2014, MAY, 12);

        //when
        final LocalDate nextHoliday = today.with(nextHoliday());

        //then
        assertThat(nextHoliday).isEqualTo(LocalDate.of(2014, MAY, 17));
    }

    private TemporalAdjuster nextHoliday() {
        return TemporalAdjusters.ofDateAdjuster(localDate -> new PolishHolidays().nextHolidayAfter(localDate));
    }

    /**
     * - Calculate time after half billion seconds from given date
     * - What was the hour in  Asia/Tokyo"
     * - See how period can be calculated
     * <p>
     * Challenging
     */
    @Test
    public void shouldFindWhenJavaHadHalfBillionthSecondBirthday() {
        //given
        final LocalDate dateOfBirth = LocalDate.of(1996, JANUARY, 23);
        final LocalTime timeOfBirth = LocalTime.of(14, 0);
        final ZonedDateTime birth = ZonedDateTime.of(dateOfBirth, timeOfBirth, ZoneId.of("America/Los_Angeles"));

        //when
        final ZonedDateTime halfBillionSecondsLater = birth.plusSeconds(500_000_000);    //?
        final int hourInTokyo = halfBillionSecondsLater.withZoneSameInstant(ZoneId.of("Asia/Tokyo")).getHour();

        //then
        assertThat(halfBillionSecondsLater.toLocalDate()).isEqualTo(LocalDate.of(2011, NOVEMBER, 27));
        assertThat(hourInTokyo).isEqualTo(7);

        //when
        final Period periodFromHalfBillionthToJava8 = Period.between(
                halfBillionSecondsLater.toLocalDate(),
                LocalDate.of(2014, MARCH, 18));

        //when
        assertThat(periodFromHalfBillionthToJava8.getYears()).isEqualTo(2);
        assertThat(periodFromHalfBillionthToJava8.getMonths()).isEqualTo(3);
    }

}
