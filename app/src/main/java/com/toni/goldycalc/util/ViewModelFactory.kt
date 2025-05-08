package com.toni.goldycalc.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.toni.goldycalc.Database.CatatanDb
import com.toni.goldycalc.navigation.DetailViewModel
import com.toni.goldycalc.ui.screen.MainViewModel

class ViewModelFactory( private val context: Context): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = CatatanDb.getInstance(context).dao
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return  MainViewModel(dao) as T
        }else if(modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(dao) as T
        }
        throw  IllegalArgumentException("Unknown viewmodel class")
    }

}