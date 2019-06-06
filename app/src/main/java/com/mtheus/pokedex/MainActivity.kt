package com.mtheus.pokedex

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import com.mtheus.pokedex.app.PokemonsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startFragment(savedInstanceState)


    }

    private fun startFragment(savedInstanceState: Bundle?) {

//        val pokemonsFragment = PokemonsFragment()
//        this.supportFragmentManager.beginTransaction()
//            .replace(R.id.container, pokemonsFragment)
//            .commit()

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container, PokemonsFragment(), null)
        fragmentTransaction.commit()
//        }
    }

}
