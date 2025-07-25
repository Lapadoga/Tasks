package com.example.tasks.timer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TimerScreen(
    viewModel: TimerViewModel
) {
    val state by viewModel.state.collectAsState()
    TimerBox(state.text)
}

@Composable
fun TimerBox(timer: String) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = timer,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}