package com.datascope.architect.ioc

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

val jsonMapper by lazy {
    ObjectMapper().apply {
        disable(FAIL_ON_UNKNOWN_PROPERTIES)
        configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
        configure(MapperFeature.AUTO_DETECT_GETTERS, false)
        configure(MapperFeature.AUTO_DETECT_SETTERS, false)
        configure(MapperFeature.AUTO_DETECT_IS_GETTERS, false)
        setVisibility(
            serializationConfig.defaultVisibilityChecker.withFieldVisibility(
                JsonAutoDetect.Visibility.ANY
            )
        )
    }
}

fun createAuthInterceptor(): Interceptor {
    return Interceptor { chain: Interceptor.Chain ->
        val request: Request?

        val requestBuilder = chain.request().newBuilder()

        requestBuilder.addHeader(HTTP_HEADER_TOKEN, HTTP_HEADER_DEF_TOKEN)
        requestBuilder.addHeader(HTTP_HEADER_SITE_ID, HTTP_HEADER_EMPTY_SITE_ID)

        requestBuilder
            .addHeader(HTTP_HEADER_DATABASE, "Quilt_Development")
            .addHeader(HTTP_HEADER_SETTINGS_LAB, "0")
            .addHeader(HTTP_HEADER_CLIENT, "Architect")
            .addHeader(HTTP_HEADER_VERSION_PARAMS, "0.0.1")

        request = requestBuilder.build()
        chain.proceed(request!!)
    }
}

val networkModule = module {

    single<OkHttpClient> {
        val cb = OkHttpClient.Builder()
            .protocols(listOf(Protocol.HTTP_1_1))
        createAuthInterceptor().apply { cb.addInterceptor(this) }
        cb.build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(JacksonConverterFactory.create(jsonMapper))
            .baseUrl("https://www.datascopesystem.com/DatatouchV4_dev")
            .client(get())
            .build()
    }
}

const val HTTP_HEADER_TOKEN = "Token"
const val HTTP_HEADER_SITE_ID = "SiteId"
const val HTTP_HEADER_VERSION_PARAMS = "Version"
const val HTTP_HEADER_DATABASE = "DataBase"
const val HTTP_HEADER_SETTINGS_LAB = "SettingsLabId"
const val HTTP_HEADER_CLIENT = "Client"
const val HTTP_HEADER_DEF_TOKEN = "c48e9f37-ac90-427a-9101-119c096593de"
const val HTTP_HEADER_EMPTY_SITE_ID = "0"