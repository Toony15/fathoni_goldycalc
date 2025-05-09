// util/SettingsDataStore.kt
package com.toni.goldycalc.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.toni.goldycalc.model.Tema
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// HATI-HATI: letakkan di luar kelas
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings_preference")

class SettingsDataStore(private val context: Context) {

    companion object {
        private val KEY_THEME = stringPreferencesKey("key_theme")
        private val KEY_IS_LIST = stringPreferencesKey("is_list")  // kalau masih dipakai
    }

    val themeFlow: Flow<Tema> = context.dataStore.data
        .map { prefs ->
            prefs[KEY_THEME]?.let { Tema.valueOf(it) } ?: Tema.SYSTEM
        }

    suspend fun saveTheme(theme: Tema) {
        context.dataStore.edit { prefs ->
            prefs[KEY_THEME] = theme.name
        }
    }

    // Kalau masih butuh: list/grid flow
    val layoutFlow = context.dataStore.data.map { prefs ->
        prefs[KEY_IS_LIST]?.toBoolean() ?: true
    }
    suspend fun saveLayout(isList: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_IS_LIST] = isList.toString()
        }
    }
}
