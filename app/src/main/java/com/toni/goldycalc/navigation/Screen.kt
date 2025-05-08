    package com.toni.goldycalc.navigation

    import com.toni.goldycalc.ui.screen.KEY_ID_CATATAN

    sealed class Screen (val route: String) {
        data object  Home:Screen("mainScreen")
        data object About: Screen("aboutgoldyscreen")
        data object Note: Screen("NoteScreen")
        data object FormBaru: Screen("detailscreen")
        data object Formubah: Screen("detailscreen/{$KEY_ID_CATATAN}") {
            fun withId(id: Long) = "detailscreen/$id"
        }
    }