package com.example.tasks.request

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RequestScreen(
    viewModel: RequestViewModel
) {
    val state by viewModel.state.collectAsState()
    AnswerBox(state.answer)
}

@Composable
fun AnswerBox(
    text: String
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}