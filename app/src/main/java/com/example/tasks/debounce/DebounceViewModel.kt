package com.example.tasks.debounce

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.concurrent.TimeUnit

class DebounceViewModel : ViewModel() {
    private val _state: MutableStateFlow<DebounceScreenState> = MutableStateFlow(DebounceScreenState())
    val state: StateFlow<DebounceScreenState>
        get() = _state.asStateFlow()
    private val compositeDisposable = CompositeDisposable()

    private val querySubject: PublishSubject<String> = PublishSubject.create()
    private val queryObservable: Observable<String> = querySubject
        .debounce(3L, TimeUnit.SECONDS)
        .observeOn(AndroidSchedulers.mainThread())

    init {
        val disposable = queryObservable.subscribe {
            Log.i("!!!!!", it)
        }
        compositeDisposable.add(disposable)
    }

    fun emmitText(text: String) {
        querySubject.onNext(text)
        _state.update {
            it.copy(
                text = text
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}