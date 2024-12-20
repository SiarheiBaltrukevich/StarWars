package com.fanatics.codechallenge

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StarWarsApp : Application() {

    companion object {
        const val APOLLO_SERVER_URL = "https://apollo-fullstack-tutorial.herokuapp.com/graphql"
    }
}