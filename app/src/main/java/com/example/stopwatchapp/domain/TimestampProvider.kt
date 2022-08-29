package com.example.stopwatchapp.domain

interface TimestampProvider {
    fun getMilliseconds(): Long
}