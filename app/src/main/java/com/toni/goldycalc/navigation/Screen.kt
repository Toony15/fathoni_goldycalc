    package com.toni.goldycalc.navigation

    sealed class Screen (val route: String) {
        data object  Home:Screen("mainScreen")
        data object About: Screen("aboutgoldyscreen")
        data object Note: Screen("NoteScreen")
    }