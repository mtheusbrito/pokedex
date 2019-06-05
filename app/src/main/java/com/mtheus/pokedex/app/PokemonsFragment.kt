package com.mtheus.pokedex.app

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mtheus.pokedex.R
import com.mtheus.pokedex.infra.adapters.PokemonAdapter
import com.mtheus.pokedex.infra.api.APIService
import com.mtheus.pokedex.models.PokemonReceiver
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokemonsFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var apiService: APIService
    private lateinit var recyclerView: RecyclerView
    private lateinit var pokemonAdapter: PokemonAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mView = inflater.inflate(R.layout.fragment_pokemons, container, false)

        initComponents(mView)
        return mView

    }

    private fun initComponents(mView: View) {
        apiService = APIService()
        recyclerView = mView.findViewById(R.id.recyclerView)
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

                    showListPokemons(response.body()!!)
                    Toast.makeText(activity, "Suceeso", Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(activity, "Erro", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun showListPokemons(body: PokemonReceiver) {

        pokemonAdapter = PokemonAdapter(context as Activity, activity as AppCompatActivity, body.results!!, apiService)
        Log.v("RECEIVER", body.toString())

        recyclerView.adapter = pokemonAdapter
        var linearLayoutManager = LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
        linearLayoutManager.scrollToPosition(0)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
    }
}