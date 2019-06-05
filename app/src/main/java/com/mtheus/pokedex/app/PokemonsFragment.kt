package com.mtheus.pokedex.app

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.*
import android.widget.Toast
import com.mtheus.pokedex.R
import com.mtheus.pokedex.infra.adapters.PokemonAdapter
import com.mtheus.pokedex.infra.api.APIService
import com.mtheus.pokedex.models.PokemonReceiver
import com.mtheus.pokedex.models.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokemonsFragment : Fragment(), SearchView.OnQueryTextListener {

    var results: MutableList<Result>? = mutableListOf()


    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        val textInput: String = newText!!.toLowerCase()
        val newResults: MutableList<Result> = mutableListOf()
        Log.v("searchView", textInput)

        results?.forEach {
            if (it.name!!.toLowerCase().contains(textInput)) {

                newResults!!.add(it)
            }
        }

        pokemonAdapter.update(newResults!!)

        return true
    }

    private lateinit var mView: View
    private lateinit var apiService: APIService
    private lateinit var recyclerView: RecyclerView
    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var toolbar: Toolbar


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mView = inflater.inflate(R.layout.fragment_pokemons, container, false)
        setHasOptionsMenu(true)
        initComponents(mView)
        return mView

    }

    private fun initComponents(mView: View) {
        apiService = APIService()
        recyclerView = mView.findViewById(R.id.recyclerView)
        toolbar = mView.findViewById(R.id.toolbar) as Toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)


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
                    results = response.body()!!.results
                    showListPokemons(results!!)

                } else {
                    Toast.makeText(activity, "Erro", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun showListPokemons(results: MutableList<Result>) {

        pokemonAdapter = PokemonAdapter(context as Activity, activity as AppCompatActivity, apiService)
        pokemonAdapter.update(results)

        recyclerView.adapter = pokemonAdapter
        val linearLayoutManager = LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)
        linearLayoutManager.scrollToPosition(0)
        recyclerView.layoutManager = linearLayoutManager

        recyclerView.setHasFixedSize(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.toolbar_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView: SearchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Buscar"
        searchView.setOnQueryTextListener(this)



        super.onCreateOptionsMenu(menu, inflater)
    }

}