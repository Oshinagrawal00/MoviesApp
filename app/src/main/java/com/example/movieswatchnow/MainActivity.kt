package com.example.movieswatchnow

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.movieswatchnow.presentation.fragment.MovieDetailsFragment
import com.example.movieswatchnow.presentation.fragment.MovieListFragment
import com.example.movieswatchnow.presentation.fragment.MovieSavedFragment
import com.example.movieswatchnow.presentation.fragment.MovieSearchFragment
import com.example.movieswatchnow.presentation.viewmodel.MovieViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var bottomNav : BottomNavigationView
    private val moviesViewModel: MovieViewModel by viewModels()
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
        val data: Uri? = intent?.data
        data?.let {
            if (it.pathSegments.contains("movie")) {
                val movieId = it.lastPathSegment  // e.g., "713364"
                if (movieId != null) {
                    loadMovieById(movieId)
                }
            }
        }
    }

    private fun loadMovieById(movieId: String ) {
        moviesViewModel.setMovieItemId(movieId, "")
        val secondFragment = MovieDetailsFragment()
        // Use FragmentManager to replace the current fragment with SecondFragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView,secondFragment)
        transaction.commit()

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