package com.example.stopwatchapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stopwatchapp.data.ElapsedTimeCalculator
import com.example.stopwatchapp.data.datasource.StopwatchStateHolder
import com.example.stopwatchapp.data.repository.StopwatchStateCalculator
import com.example.stopwatchapp.databinding.ActivityMainBinding
import com.example.stopwatchapp.domain.TimestampProvider
import com.example.stopwatchapp.utils.TimestampMillisecondsFormatter
import com.example.stopwatchapp.viewmodel.StopwatchListOrchestrator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val timestampProvider = object : TimestampProvider {
        override fun getMilliseconds(): Long {
            return System.currentTimeMillis()
        }
    }

    private val stopwatchListOrchestrator = StopwatchListOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        ),
        CoroutineScope(Dispatchers.Main + SupervisorJob())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView = binding.textTime
        CoroutineScope(
            Dispatchers.Main + SupervisorJob()
        ).launch {
            stopwatchListOrchestrator.ticker.collect {
                textView.text = it
            }
        }

        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            buttonStart.setOnClickListener {
                stopwatchListOrchestrator.start()
            }

            buttonPause.setOnClickListener {
                stopwatchListOrchestrator.pause()
            }

            buttonStopText.setOnClickListener {
                stopwatchListOrchestrator.stop()
            }
        }
    }
}









