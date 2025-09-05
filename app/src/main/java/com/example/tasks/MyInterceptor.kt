package com.example.tasks

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        Log.i("NETWORK_ANSWER_CODE", response.code.toString())
        return response
    }
}