package com.mtheus.pokedex.infra.api

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import com.mtheus.pokedex.app.MyAplication
import com.mtheus.pokedex.infra.api.endPoints.PokemonReceiverEndPoint
import com.mtheus.pokedex.utils.ConstantUtils
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIService {

    private var retrofit: Retrofit
    private var okHttpClient: OkHttpClient
    var pokemonReceiverEndPoint: PokemonReceiverEndPoint


    init {
        val cacheSize = 50 * 1024 * 1024 // 50 mb ...tamanho a ser alocado na memória
        val cache = Cache(MyAplication.context!!.cacheDir, cacheSize.toLong())

        okHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor { chain ->

                var request = chain.request()
                request = if (hasNetwork()!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                //Se não houver internet, obtem o cache que foi armazenado há 7 dias.
                //Se o cache tiver mais de 7 dias, é descartado...
                    request.newBuilder().header(

                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                chain.proceed(request)
            }

            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(ConstantUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        pokemonReceiverEndPoint = this.retrofit.create(PokemonReceiverEndPoint::class.java)
    }

    fun hasNetwork(): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager =
            MyAplication.context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }


}