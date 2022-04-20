package com.zcdorman.smallglider.network.client

import com.zcdorman.smallglider.extension.toCustomNetworkError
import com.zcdorman.smallglider.model.error.CustomNetworkError
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.gson.*
import java.util.concurrent.TimeUnit

/**
 * 通信、API、向き先、設定により
 * 幾つかのClientを作る
 *
 */
object Clients {
    private val TIME_OUT = TimeUnit.SECONDS.toMillis(10)

    val defaultClient by lazy {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                gson {
                    setPrettyPrinting()
                }
            }
            engine {
                requestTimeout = TIME_OUT
            }
            install(Logging) {
                level = LogLevel.ALL
            }
            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, request ->
                    val customNetworkError = when (exception) {
                        is ResponseException -> exception.response.status.toCustomNetworkError()
                        is ClientRequestException -> exception.response.status.toCustomNetworkError()
                        else -> CustomNetworkError.OtherError
                    }
                    throw customNetworkError
                }
            }
        }
    }

    /**
     * アプリを終了するときに
     * 全てのClientをcloseするため
     */
    fun closeAll() {
        defaultClient.close()
    }
}