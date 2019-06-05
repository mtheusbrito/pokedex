package com.mtheus.pokedex.infra.adapters

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mtheus.pokedex.R
import com.mtheus.pokedex.infra.api.APIService
import com.mtheus.pokedex.models.Pokemon
import com.mtheus.pokedex.models.Result
import kotlinx.android.synthetic.main.pokemon_row.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class PokemonAdapter(
    private val context: Activity,
    private val activity: AppCompatActivity,
    private var apiService: APIService
) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private lateinit var results: MutableList<Result>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.pokemon_row, parent, false))


    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (results[position].name!!.isNotEmpty()) {
            position

        } else {
            -1
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result: Result = results[position]
        holder.bindView(result)


        try {
            val call = apiService.pokemonReceiverEndPoint.getPokemon(result.name!!)
            call.enqueue(object : Callback<Pokemon> {
                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    Log.v("Error sprite", t.message)
                }

                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if (response.isSuccessful) {
                        holder.setSprite(response.body()!!.sprites?.front_default.toString(), context)
                        Log.v("Sprite", response.body()!!.sprites?.front_default.toString())
                    } else {
                        Log.v("Error sprite", "response is not success")
                    }
                }

            })
        } catch (e: Exception) {
            Log.v("error sprite", e.message)
        }
    }

    fun update(resultsUp: MutableList<Result>) {
        results = resultsUp
        notifyDataSetChanged()


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val texViewName = itemView.pokemon_name
        val imageViewPokemon = itemView.pokemon_image


        fun bindView(result: Result) {

            texViewName.text = result.name?.capitalize() ?: null
        }

        fun setSprite(sprite: String, context: Activity) {
            Glide.with(context).load(sprite).apply(RequestOptions.circleCropTransform()).diskCacheStrategy(
                DiskCacheStrategy.ALL
            ).into(imageViewPokemon)

        }

    }
}