package com.example.stopwatchapp.utils

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TimestampMillisecondsFormatterTest {

    private lateinit var formatter: TimestampMillisecondsFormatter

    @Before
    fun setup() {
        formatter = TimestampMillisecondsFormatter()
    }

    @Test
    fun formatter_equalsTimeMilliseconds_returnTrue() {
        val timestamp = 256L
        val expectedTimeString = "00:00:256"

        val actualTimeString = formatter.format(timestamp)

        assertEquals(expectedTimeString, actualTimeString)
    }

    @Test
    fun formatter_equalsTimeMinutesAndMilliseconds_returnTrue() {
        val timestamp = 15_007L
        val expectedTimeString = "00:15:007"

        val actualTimeString = formatter.format(timestamp)

        assertEquals(expectedTimeString, actualTimeString)
    }

    @Test
    fun formatter_equalsTimeMoreMinutesAndMilliseconds_returnTrue() { // 1000 * 60 * 59
        val timestamp = 3_567_578L
        val expectedTimeString = "59:27:578"

        val actualTimeString = formatter.format(timestamp)

        assertEquals(expectedTimeString, actualTimeString)
    }

    @Test
    fun formatter_equalsTimeZero_returnTrue() {
        val timestamp = 0L
        val expectedTimeString = "00:00:000"

        val actualTimeString = formatter.format(timestamp)

        assertEquals(expectedTimeString, actualTimeString)
    }

    @Test
    fun formatter_notEqualsTimeZero_returnTrue() {
        val timestamp = 3_567_578L
        val expectedTimeString = "00:00:000"

        val actualTimeString = formatter.format(timestamp)

        assertNotEquals(expectedTimeString, actualTimeString)
    }

    @Test
    fun formatter_WithHoursTimestamp_returnTrue() {
        val timestamp = 39_725_000L
        val expectedTimeString = "11:02:05"

        val actualTimeString = formatter.format(timestamp)

        assertEquals(expectedTimeString, actualTimeString)
    }

    @Test
    fun formatter_equalsMinutes_returnTrue() {
        val timeString = "11:02:05"
        val expectedTime = 2L

        val actualTime = formatter.extractMinutes(timeString)

        assertEquals(expectedTime, actualTime)
    }

    @Test
    fun formatter_equalsMinutes_returnFalse() {
        val timeString = "11:02:05"
        val expectedTime = 3L

        val actualTime = formatter.extractMinutes(timeString)

        assertNotEquals(expectedTime, actualTime)
    }

    @Test
    fun formatter_extractHours_notNull() {
        val timeString = "12:02:05"

        val actualHours = formatter.extractHours(timeString)

        assertNotNull(actualHours)
    }

    @Test
    fun formatter_extractHours_withNull() {
        val timeString = "00:02:05"

        val actualHours = formatter.extractHours(timeString)

        assertNull(actualHours)
    }

    @Test
    fun formatter_equalsArrayMinutes_returnTrue() {
        val timeWithHours1 = "05:02:14"
        val timeWithHours2 = "02:15:11"
        val timeWithHours3 = "00:00:00"
        val timeNotWithHours1 = "02:15:144"
        val timeNotWithHours2 = "15:45:116"
        val timeNotWithHours3 = "00:10:194"

        assertArrayEquals(
            longArrayOf(
                formatter.extractMinutes(timeWithHours1),
                formatter.extractMinutes(timeWithHours2),
                formatter.extractMinutes(timeWithHours3)
            ),
            longArrayOf(
                formatter.extractMinutes(timeNotWithHours1),
                formatter.extractMinutes(timeNotWithHours2),
                formatter.extractMinutes(timeNotWithHours3)
            )
        )
    }

    // Весьма специфичный тест, который показывает, что для небольших значений ссылки будут равны
    @Test
    fun optimizationNotFantasy() {
        val timeWithHours1 = "05:02:14"
        val timeNotWithHours1 = "02:15:144"

        assertSame(formatter.extractMinutes(timeWithHours1) ,formatter.extractMinutes(timeNotWithHours1))
    }
}

