package com.example.movieswatchnow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.movieswatchnow.presentation.fragment.MovieListFragment
import com.example.movieswatchnow.presentation.fragment.MovieSavedFragment
import com.example.movieswatchnow.presentation.fragment.MovieSearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(MovieListFragment())
        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home_menu -> loadFragment(MovieListFragment())
                R.id.saved_menu -> loadFragment(MovieSavedFragment())
                R.id.search_menu -> loadFragment(MovieSearchFragment())
            }
            true
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)

        val navHostFragment : NavHostFragment = NavHostFragment.create(R.navigation.movies_nav_graph)


        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.movies_nav_graph)
        navController.graph = navGraph

    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView,fragment)
        transaction.commit()
    }
}