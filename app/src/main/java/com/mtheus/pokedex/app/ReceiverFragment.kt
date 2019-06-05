package com.mtheus.pokedex.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mtheus.pokedex.R
import com.mtheus.pokedex.infra.api.APIService
import com.mtheus.pokedex.models.PokemonReceiver
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ReceiverFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var apiService: APIService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mView = inflater.inflate(R.layout.fragment_receiver, container, false)

        initComponents()
        return mView

    }

    private fun initComponents() {
        apiService = APIService()
        getPokemons()
    }

    private fun getPokemons() {
        val call = apiService.pokemonReceiverEndPoint.getPokemons()
        call.enqueue(object : Callback<PokemonReceiver> {
            override fun onFailure(call: Call<PokemonReceiver>, t: Throwable) {
                Toast.makeText(activity, "Falha na conexão" + t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<PokemonReceiver>, response: Response<PokemonReceiver>) {
                if (response.isSuccessful) {

                    Toast.makeText(activity, "Suceeso", Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(activity, "Erro", Toast.LENGTH_LONG).show()
                }
            }

        })
    }
}