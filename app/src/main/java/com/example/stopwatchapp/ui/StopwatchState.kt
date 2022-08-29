package com.example.stopwatchapp.ui

sealed class StopwatchState {
    data class Paused(val elapsedTime: Long) : StopwatchState()
    data class Running(val startTime: Long, val elapsedTime: Long) : StopwatchState()
}
