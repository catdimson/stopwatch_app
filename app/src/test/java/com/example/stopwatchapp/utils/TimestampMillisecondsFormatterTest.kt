package com.example.stopwatchapp.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
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
}

