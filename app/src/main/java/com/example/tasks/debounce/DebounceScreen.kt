package com.example.tasks.debounce

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DebounceScreen(
    viewModel: DebounceViewModel
) {
    val state by viewModel.state.collectAsState()
    TextBox(state.text) {
        viewModel.emmitText(it)
    }
}

@Composable
fun TextBox(
    text: String,
    onValueChange: (String) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            modifier = Modifier.align(Alignment.Center),
            value = text,
            onValueChange = {
                onValueChange(it)
            }
        )
    }
}