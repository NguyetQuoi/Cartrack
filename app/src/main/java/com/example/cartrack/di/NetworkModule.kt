package com.example.cartrack.di
import com.example.cartrack.BuildConfig
import com.example.cartrack.global.RxErrorHandlingCallAdapterFactory
import com.example.cartrack.util.DeviceUtils
import com.example.cartrack.util.rx.SchedulerProvider
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        Interceptor { chain ->
            val originalRequest = chain.request()
            val builder = originalRequest.newBuilder()
            val newRequest = builder.build()
            chain.proceed(newRequest)
        }
    }

    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(get())
            .addInterceptor(loggingInterceptor)
            .connectTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .cache(
                Cache(
                    File(androidContext().cacheDir, "OkCache"),
                DeviceUtils.calcCacheSize(androidContext(), .25f))
            )
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create(get<SchedulerProvider>().io()))
            .client(get()).build()
    }
}