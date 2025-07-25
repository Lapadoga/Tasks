package com.example.tasks.timer

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.concurrent.TimeUnit

class TimerViewModel : ViewModel() {
    private val _state: MutableStateFlow<TimerScreenState> = MutableStateFlow(TimerScreenState())
    val state: StateFlow<TimerScreenState>
        get() = _state.asStateFlow()

    private var disposable: Disposable = Observable.interval(1, TimeUnit.SECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { tick ->
            _state.update {
                it.copy(
                    text = "Таймер: $tick сек."
                )
            }
        }

    private fun stopTimer() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopTimer()
    }
}