package com.zcdorman.smallglider.network.client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import java.util.concurrent.TimeUnit

/**
 * 通信、API、向き先、設定により
 * 幾つかのClientを作る
 *
 * TODO：エラー対応
 */
object Clients {
    val defaultClient by lazy {
        HttpClient(CIO) {
            install(JsonFeature) {
                serializer = GsonSerializer {
                    setPrettyPrinting()
                }
            }
            engine {
                requestTimeout = TimeUnit.SECONDS.toMillis(10)
            }
            // エラー対応のため
            // install(ResponseObserver){onResponse{}}
            // HttpResponseValidator (validateResponse, handleResponseException)
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