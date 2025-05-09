package com.toni.goldycalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.toni.goldycalc.navigation.SetupNavbarGraph
import com.toni.goldycalc.ui.theme.GoldyCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoldyCalcTheme {
             SetupNavbarGraph()
            }
        }
    }
}



