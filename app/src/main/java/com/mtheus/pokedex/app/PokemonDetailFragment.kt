package com.mtheus.pokedex.app


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

import com.mtheus.pokedex.R
import com.mtheus.pokedex.infra.api.APIService
import com.mtheus.pokedex.models.Pokemon
import com.mtheus.pokedex.utils.ConstantUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class PokemonDetailFragment : Fragment() {
    private lateinit var mView: View
    private var apiService: APIService = APIService()
    private lateinit var imageView: ImageView
    private lateinit var textViewName: TextView
    private lateinit var textViewAbilities: TextView
    private lateinit var textViewBaseExperience: TextView
    private lateinit var textViewHeight: TextView


    fun newInstance(name: String): PokemonDetailFragment {
        val pokemonDetailFragment = PokemonDetailFragment()
        val bundle = Bundle()
        bundle.putString(ConstantUtils.NAME, name)
        pokemonDetailFragment.arguments = bundle
        return pokemonDetailFragment

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        mView = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
        initComponents(mView)
        return mView;
    }

    private fun initComponents(mView: View) {
        textViewName = mView.findViewById(R.id.pokemon_name)
        imageView = mView.findViewById(R.id.pokemon_image)
        textViewBaseExperience = mView.findViewById(R.id.pokemon_base_experience)
        textViewAbilities = mView.findViewById(R.id.abilities)
        textViewHeight = mView.findViewById(R.id.pokemon_height)
        retrieveName()
    }

    private fun retrieveName() {

        arguments?.getString(ConstantUtils.NAME)!!.let {
            val name: String = it
            getPokemon(name)
        }
    }

    private fun getPokemon(name: String) {



        apiService.pokemonReceiverEndPoint.getPokemon(name).enqueue(object : Callback<Pokemon> {
            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                Toast.makeText(activity, "Error\n" + t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {

                if (response.isSuccessful) {

                    showPokemonAttributes(response.body())
                } else {
                    Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    @SuppressLint("SetTextI18n")
    private fun showPokemonAttributes(pokemon: Pokemon?) {
        textViewName.text = pokemon?.name?.capitalize()
        pokemon?.abilities?.forEach {
            textViewAbilities.text = textViewAbilities.text.toString() + " " + it.ability?.name?.capitalize()
        }
        textViewBaseExperience.text = pokemon?.base_experience.toString()
        textViewHeight.text = pokemon?.height.toString()

        try {
            Glide.with(this).load(pokemon?.sprites?.front_default).apply(RequestOptions.circleCropTransform())
                .diskCacheStrategy(
                    DiskCacheStrategy.ALL
                ).into(imageView)
        } catch (e: Exception) {
            Log.v("Error", e.message)
        }
    }


}
