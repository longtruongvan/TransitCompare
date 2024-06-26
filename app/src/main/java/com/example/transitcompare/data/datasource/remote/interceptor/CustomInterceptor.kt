package com.example.transitcompare.data.datasource.remote.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer


class CustomInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()

        val request: Request = original
            .newBuilder()
            .method(original.method(), original.body())
            .build()

        //LOGGING REQUEST AND RESPONSE
        val requestTime = System.nanoTime()
        val requestBuffer: Buffer = Buffer()
        request.body()?.writeTo(requestBuffer)

        val response = chain.proceed(request)
        val responseTime = System.nanoTime()

        var content = ""
        if (response.body() != null) {
            content = response.body()!!.string()
        }
        val contentBody = ((("""🔗 API
                ==== RESPONSE in ${((responseTime - requestTime) / 1e6).toInt()}ms ====
                 Method ${request.method()}""".toString() +
                "\nHeaders: " + request.headers().toString()).toString() +
                "\nURL: " + request.url()).toString() +
                "\nRequest Body: " + requestBuffer.readUtf8()).toString() +
                "\nURL: " + response.request().url() +
                "\nResponse: " + content +
                "\n==== END ====" +
                "\n "
        Log.d("BoostLogger", contentBody)

        val contentType = response.body()!!.contentType()
        val wrappedBody = ResponseBody.create(contentType, content)
        return response.newBuilder().body(wrappedBody).build()
    }
}