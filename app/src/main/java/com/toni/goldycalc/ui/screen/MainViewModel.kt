package com.toni.goldycalc.ui.screen

import androidx.lifecycle.ViewModel
import com.toni.goldycalc.model.Catatan




    class MainViewModel : ViewModel() {
        val  data = listOf(
            Catatan (
                1,
                "Kuliah Mobpro 17 Feb",
                "Kuliah hari pertama. Ternyata keren juga yang mau dipelajari.", "2025-02-17 12:34:56"
            ),
            Catatan (
                2,
                "Kuliah Mobpro 19 Feb",
                "Praktikum pertama: running modul. Alhamdulillah hari ini lancar.",
                "2025-02-19 12:34:56"
            ),
            Catatan (
                3,
                "Kuliah Mobpro 25 Feb",
                "Kuliah hari pertama. Ternyata keren juga yang mau dipelajari.",
                "2025-02-17 12:34:56"
            ),
            Catatan (
                1,
                "Kuliah Mobpro 30 Feb",
                "Kuliah hari pertama. Ternyata keren juga yang mau dipelajari.",
                "2025-02-17 12:34:56"
            ),
            Catatan (
                1,
                "Kuliah Mobpro 5 Feb",
                "Kuliah hari pertama. Ternyata keren juga yang mau dipelajari.",
                "2025-02-17 12:34:56"
            ),
            Catatan (
                1,
                "Kuliah Mobpro 10 Feb",
                "Kuliah hari pertama. Ternyata keren juga yang mau dipelajari.",
                "2025-02-17 12:34:56"
            ),


            )
        fun getCatatan(id: Long): Catatan? {
            return data.find { it.id == id }
        }
    }
