package com.myapp.movieswatchnow.presentation.utils

import com.myapp.movieswatchnow.domain.Genres
import com.myapp.movieswatchnow.domain.SpokenLanguages

object MovieHelperUtil {
     fun getSpokenLanguages(spokenLanguages: ArrayList<SpokenLanguages>): String {
        var spokenLanguage = ""
        spokenLanguages.forEach {
            spokenLanguage = spokenLanguage + it.name + ", "
        }
        return spokenLanguage.ifEmpty {
            "English"
        }
    }

     fun getGenresList(genres: ArrayList<Genres>): String {
        var genreString = ""
        genres.forEach {
            genreString = genreString + it.name + ", "
        }
        return genreString.ifEmpty {
            "Funny"
        }
    }

     fun getOriginCountry(country: ArrayList<String>?): String {
        var countryResponse = ""
        return if (country?.isNotEmpty() == true) {
            country.forEach {
                countryResponse += "$it, "
            }
            countryResponse
        } else {
            "USA"
        }
    }
}