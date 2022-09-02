package com.example.stopwatchapp.utils

import java.util.regex.Pattern

const val CONTAINS_HOURS_PATTERN = """^(?<hours>\d{2}):(?<minutes>\d{2}):(?<seconds>\d{2})$"""
const val NOT_CONTAINS_HOURS_PATTERN = """^(?<minutes>\d{2}):(?<seconds>\d{2}):(?<milliseconds>\d{3})$"""

class TimestampMillisecondsFormatter {

    fun format(timestamp: Long): String {
        val millisecondsFormatted = (timestamp % 1000).pad(3)
        val seconds = timestamp / 1000
        val secondsFormatted = (seconds % 60).pad(2)
        val minutes = seconds / 60
        val minutesFormatted = (minutes % 60).pad(2)
        val hours = minutes / 60

        return if (hours > 0) {
            val hoursFormatted = (minutes / 60).pad(2)
            "$hoursFormatted:$minutesFormatted:$secondsFormatted"
        } else {
            "$minutesFormatted:$secondsFormatted:$millisecondsFormatted"
        }
    }

    private fun Long.pad(desiredLength: Int) = this.toString().padStart(desiredLength, '0')

    fun extractHours(time: String) : Long? {
        val containHoursPattern = Pattern.compile(CONTAINS_HOURS_PATTERN)
        val matcher = containHoursPattern.matcher(time)
        while (matcher.matches()) {
            val hours = matcher.group("hours")!!.toLong()
            return if (hours == 0L) null else hours
        }
        return null
    }

    fun extractMinutes(time: String) : Long {
        val containHoursPattern = Pattern.compile(CONTAINS_HOURS_PATTERN)
        val notContainHoursPattern = Pattern.compile(NOT_CONTAINS_HOURS_PATTERN)
        val matcherContainHours = containHoursPattern.matcher(time)
        val matcherNotContainHours = notContainHoursPattern.matcher(time)

        return if (matcherContainHours.matches()) {
            matcherContainHours.group("minutes")!!.toLong()
        } else {
            if (matcherNotContainHours.matches()) {
                matcherNotContainHours.group("minutes")!!.toLong()
            } else 0L
        }
    }
}