package com.example.tasks.request

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RequestViewModel : ViewModel() {

    private val _state: MutableStateFlow<RequestScreenState> =
        MutableStateFlow(RequestScreenState())
    val state: StateFlow<RequestScreenState>
        get() = _state.asStateFlow()
    private val compositeDisposable = CompositeDisposable()

    init {
        val disposable = API.service.randomImage()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { answer -> _state.update { it.copy(answer.message) } },
                { error -> Log.e("!!!", error.message.toString()) }
            )

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}