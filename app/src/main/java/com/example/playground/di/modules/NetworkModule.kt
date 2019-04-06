package com.example.playground.di.modules

import android.content.Context
import com.example.playground.data.network.PostApi
import com.example.playground.util.BASE_URL
import com.example.playground.util.diUtil.ConnectionCheckModule
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Defines all the network classes that need to be provided in the scope of the app.
 *
 * Define here all objects that are shared throughout the app, like [retrofitInstanceProvider].
 * If some of those objects are singletons, they should be annotated with `@Singleton`.
 */
@Module(includes = [AppModule::class, ConnectionCheckModule::class])
class NetworkModule{

    private var isOnline: Boolean = false
    private lateinit var context: Context
    private val Cache_Control = "cache-control"

    /**
     * Single instance is created of Retrofit ApiService through out the Application
     */

    @Singleton
    @Provides
    fun retrofitInstanceProvider(@Named("AppContext") context: Context,
                                 @Named("isOnline") isOnline : Boolean): PostApi{
        this.isOnline = isOnline
        this.context = context
        return retrofitProvider().create(PostApi::class.java)
    }

    /**
     * if we have more than one Api classes [@GET, @POST] we have to use @Provides here too.
     */
    @Singleton
    fun retrofitProvider(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient())
            .build()

    }

    private fun okHttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(provideOfflineCacheInterceptor())
            .addNetworkInterceptor(provideCacheInterceptor())
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .cache(provideCache())
            .build()
    }

    private fun provideOfflineCacheInterceptor() =
        Interceptor { chain ->
            var request = chain.request()
            request = if (isOnline) {
                val cacheControl = CacheControl.Builder().maxStale(7, TimeUnit.DAYS).build()
                request.newBuilder().cacheControl(cacheControl).build()
            } else {
                val cacheHeaderValue = if (isOnline)
                    "public, max-age=2419200"
                else
                    "public, only-if-cached, max-stale=2419200"
                request.newBuilder().header("Cache-Control", cacheHeaderValue)
                    .build()
            }
            chain.proceed(request)
        }

    /**
     * This interceptor will be provided to NetworkInterceptor
     *
     * NetworkInterceptor is added to deal with header of the request
     *
     */
    private fun provideCacheInterceptor() = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder().maxStale(2, TimeUnit.DAYS).build()
        // removeHeader("Pragma") is used for removing Pragma : No-Cache
        response.newBuilder().removeHeader("Pragma").header(Cache_Control, cacheControl.toString()).build()
    }

    private fun provideCache(): Cache? {
        var cache: Cache? = null
        try {
            cache = Cache(File(context.cacheDir, "http-cache"), (10 * 1024 * 1024).toLong())

        } catch (e: Exception) {
            println("Error " + e.message)
        }
        return cache
    }

}