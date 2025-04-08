package com.toni.goldycalc.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.toni.goldycalc.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutGoldyScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "GoldyCalc",
                        style = MaterialTheme.typography.headlineSmall
                    )
                },

                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFFDAA520),
                    titleContentColor = Color.White,
                ),
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.Kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )

        }
    ) { innerPadding ->
       Text(
           text = stringResource(R.string.penjelasan),
           modifier = Modifier.padding(innerPadding).padding(16.dp)
       )
    }
}
@Composable
fun AboutScreenPreview() {
    AboutGoldyScreen(rememberNavController())
}